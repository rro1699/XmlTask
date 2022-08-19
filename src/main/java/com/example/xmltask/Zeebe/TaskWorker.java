package com.example.xmltask.Zeebe;

import com.example.xmltask.Service.XmlToCsvService;
import com.example.xmltask.Zeebe.UtilsModel.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class TaskWorker {

    private XmlToCsvService xmlToCsvService;

    public TaskWorker(XmlToCsvService xmlToCsvService) {
        this.xmlToCsvService = xmlToCsvService;
    }

    @ZeebeWorker(type = "xml-parse")
    public void parseXML(final JobClient client, final ActivatedJob job) {
        String fileName = job.getVariablesAsMap().get("path").toString();
        String prefix = job.getVariablesAsMap().get("prefix").toString();
        StringBuilder sb = new StringBuilder();
        String dataToAnswer = null;
        try {
            Files.readAllLines(Paths.get(fileName)).forEach(obj->sb.append(obj));
            ResponseEntity response =  xmlToCsvService.parse(sb.toString(),prefix);
            if(response.getStatusCode().is2xxSuccessful()){
                dataToAnswer = createDataForAnswer(response.getStatusCode().toString(),
                        response.getHeaders().getLocation().toString());
            }
            else{
                dataToAnswer = createDataForAnswer(response.getStatusCode().toString(), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            dataToAnswer = createDataForAnswer(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
        }
        client.newCompleteCommand(job.getKey())
                .variables("{\"answer\":"+dataToAnswer+"}")
                .send()
                .exceptionally(throwable ->
                {throw  new RuntimeException("Could not complete job", throwable);});
        System.out.println("{\"answer\":"+dataToAnswer+"}");
    }

    private String createDataForAnswer(String status, String location){
        ObjectMapper objectMapper = new ObjectMapper();
        String dataToAnswer = null;
        try {
            dataToAnswer = objectMapper.writeValueAsString(new Answer(status,location));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dataToAnswer;
    }
}

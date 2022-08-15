package com.example.xmltask.Zeebe;

import com.example.xmltask.Service.XmlToCsvService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
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
        try {
            Files.readAllLines(Paths.get(fileName)).forEach(obj->sb.append(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseEntity response =  xmlToCsvService.parse(sb.toString(),prefix);
            client.newCompleteCommand(job.getKey())
                    .variables("{\"answer\":\"Status code="+response.getStatusCode().toString()+", Location="
                            +response.getHeaders().getLocation()+"\"}")
                    .send()
                    .exceptionally(throwable ->
                    {throw  new RuntimeException("Could not complete job", throwable);});
        System.out.println("{\"answer\":\"Status code="+response.getStatusCode().toString()+", Location="
                +response.getHeaders().getLocation()+"\"}");
    }
}

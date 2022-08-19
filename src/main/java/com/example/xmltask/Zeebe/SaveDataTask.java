package com.example.xmltask.Zeebe;

import com.example.xmltask.Service.SaveDataService;
import com.example.xmltask.Zeebe.UtilsModel.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SaveDataTask {
    private SaveDataService saveDataService;

    public SaveDataTask(SaveDataService saveDataService) {
        this.saveDataService = saveDataService;
    }

    @ZeebeWorker(type = "saveDataFromCsvIntoDB")
    public void saveData(final JobClient client, final ActivatedJob job) {
        String filePath = job.getVariablesAsMap().get("data").toString().substring(6);
        ResponseEntity response = saveDataService.saveData(filePath);
        client.newCompleteCommand(job.getKey())
                .variables("{\"statusCode\":\""+response.getStatusCode().toString()+"\"}")
                .send()
                .exceptionally(throwable ->
                {throw  new RuntimeException("Could not complete job", throwable);});
        System.out.println("{\"statusCode\":"+response.getStatusCode().toString()+"}");
    }

}

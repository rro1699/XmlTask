package com.example.xmltask.Contollers;

import com.example.xmltask.Service.SaveDataService;
import com.example.xmltask.Service.XmlToCsvService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    private XmlToCsvService xmlService;
    private SaveDataService saveDataService;

    public MainController(XmlToCsvService xmlService, SaveDataService saveDataService) {
        this.xmlService = xmlService;
        this.saveDataService = saveDataService;
    }

    @PostMapping(value = "/createCSV", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity parsingXML(@RequestBody String body){
        return xmlService.parse(body);
    }

    @GetMapping(value = "/getAllValues")
    public ResponseEntity getAllValuesFromCsv(){
        return saveDataService.getAllLines();
    }
}

package com.example.xmltask.Contollers;

import com.example.xmltask.Service.XmlToCsvService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    private XmlToCsvService xmlService;

    public MainController(XmlToCsvService xmlService) {
        this.xmlService = xmlService;
    }

    @PostMapping(value = "/createCSV", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity parsingXML(@RequestBody String body){
        return xmlService.parse(body);
    }
}

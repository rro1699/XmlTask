package com.example.xmltask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;


@Data
@JacksonXmlRootElement(localName = "DATAPACKET")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datapacket {
    @JacksonXmlProperty(localName = "NE")
    private Ne ne;

    @JacksonXmlElementWrapper(localName = "TABLES")
    @JacksonXmlProperty(localName = "TABLE")
    private List<Table> tableList;

}

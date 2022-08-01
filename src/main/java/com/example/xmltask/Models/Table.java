package com.example.xmltask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;


@Data
@JacksonXmlRootElement(localName = "TABLE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {
    @JacksonXmlProperty(isAttribute = true)
    private String attrname;

    @JacksonXmlElementWrapper(localName = "ROWDATA")
    @JacksonXmlProperty(localName = "ROW")
    private List<Row> rowList;

}

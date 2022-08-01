package com.example.xmltask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


@Data
@JacksonXmlRootElement(localName = "NE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ne {
    @JacksonXmlProperty(isAttribute = true)
    private String NEFdn;
    @JacksonXmlProperty(isAttribute = true)
    private String NEName;
    @JacksonXmlProperty(isAttribute = true)
    private String NEType;

}

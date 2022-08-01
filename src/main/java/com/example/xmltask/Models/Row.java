package com.example.xmltask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ROW")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Row {
    @JacksonXmlProperty(isAttribute = true)
    private String RackNo;
    @JacksonXmlProperty(isAttribute = true)
    private String FrameNo;
    @JacksonXmlProperty(isAttribute = true)
    private String SlotNo;
    @JacksonXmlProperty(isAttribute = true)
    private String SlotPos;
    @JacksonXmlProperty(isAttribute = true)
    private String InventoryUnitId;
    @JacksonXmlProperty(isAttribute = true)
    private String RackType;
    @JacksonXmlProperty(isAttribute = true)
    private String InventoryUnitType;
    @JacksonXmlProperty(isAttribute = true)
    private String VendorUnitFamilyType;
    @JacksonXmlProperty(isAttribute = true)
    private String VendorUnitTypeNumber;
    @JacksonXmlProperty(isAttribute = true)
    private String VendorName;
    @JacksonXmlProperty(isAttribute = true)
    private String SerialNumber;
    @JacksonXmlProperty(isAttribute = true)
    private String HardwareVersion;
    @JacksonXmlProperty(isAttribute = true)
    private String DateOfManufacture;
    @JacksonXmlProperty(isAttribute = true)
    private String DateOfLastService;
    @JacksonXmlProperty(isAttribute = true)
    private String UnitPosition;
    @JacksonXmlProperty(isAttribute = true)
    private String ManufacturerData;
    @JacksonXmlProperty(isAttribute = true)
    private String UserLabel;
    @JacksonXmlProperty(isAttribute = true)
    private String FrameType;
    @JacksonXmlProperty(isAttribute = true)
    private String RackFrameNo;
    @JacksonXmlProperty(isAttribute = true)
    private String ModuleNo;
    @JacksonXmlProperty(isAttribute = true)
    private String SubSlotNo;
    @JacksonXmlProperty(isAttribute = true)
    private String BoardName;
    @JacksonXmlProperty(isAttribute = true)
    private String BoardType;
    @JacksonXmlProperty(isAttribute = true)
    private String SoftVer;
    @JacksonXmlProperty(isAttribute = true)
    private String LogicVer;
    @JacksonXmlProperty(isAttribute = true)
    private String BiosVer;
    @JacksonXmlProperty(isAttribute = true)
    private String BiosVerEx;
    @JacksonXmlProperty(isAttribute = true)
    private String LANVer;
    @JacksonXmlProperty(isAttribute = true)
    private String MBUSVer;
    @JacksonXmlProperty(isAttribute = true)
    private String IssueNumber;
    @JacksonXmlProperty(isAttribute = true)
    private String BOMCode;
    @JacksonXmlProperty(isAttribute = true)
    private String PortNo;
    @JacksonXmlProperty(isAttribute = true)
    private String MacAddr;
    @JacksonXmlProperty(isAttribute = true)
    private String HostVerType;
    @JacksonXmlProperty(isAttribute = true)
    private String HostVer;
    @JacksonXmlProperty(isAttribute = true)
    private String sDesc;
}

package com.example.xmltask.Models;

import lombok.Getter;

@Getter
public class SlotMappingTable {
    private String EID;
    private String NAME;
    private String SLOTTYPE;
    private String SHELFEID;
    private String PARENTCARDEID;
    private String SOURCE;

    public SlotMappingTable(Row row, String name) {
        setEID(row,name);
        setNAME(row);
        setSLOTTYPE();
        setSHELFEID(row, name);
        setPARENTCARDEID();
        setSOURCE();
    }

    public SlotMappingTable(Row row, String name,String prefix) {
        setEID(row,name,prefix);
        setNAME(row);
        setSLOTTYPE();
        setSHELFEID(row, name,prefix);
        setPARENTCARDEID();
        setSOURCE();
    }

    private void setEID(Row row, String name){
        StringBuilder sb = new StringBuilder();
        sb.append("<prefix>:").append(name).append("-")
                .append(row.getRackNo()).append("/")
                .append(row.getFrameNo()).append("/")
                .append(row.getSlotPos()).append("/")
                .append(row.getSlotNo());
        if(row.getSubSlotNo() != null)
            sb.append("/").append(row.getSubSlotNo());
        EID = sb.toString();
    }

    private void setEID(Row row, String name,String prefix){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(":").append(name).append("-")
                .append(row.getRackNo()).append("/")
                .append(row.getFrameNo()).append("/")
                .append(row.getSlotPos()).append("/")
                .append(row.getSlotNo());
        if(row.getSubSlotNo() != null)
            sb.append("/").append(row.getSubSlotNo());
        EID = sb.toString();
    }

    private void setNAME(Row row){
        StringBuilder sb = new StringBuilder();
        sb.append("Slot-").append(row.getSlotPos())
                .append("/").append(row.getSlotNo());
        if(row.getSubSlotNo() != null)
            sb.append("/").append(row.getSubSlotNo());
        NAME = sb.toString();
    }

    private void setSLOTTYPE(){
            SLOTTYPE = "SLOT";
    }

    private void setSHELFEID(Row row, String name){
        StringBuilder sb = new StringBuilder();
        sb.append("<prefix>:").append(name).append("-")
                .append(row.getRackNo()).append("/")
                .append(row.getFrameNo());
        SHELFEID = sb.toString();
    }

    private void setSHELFEID(Row row, String name,String prefix){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(":").append(name).append("-")
                .append(row.getRackNo()).append("/")
                .append(row.getFrameNo());
        SHELFEID = sb.toString();
    }

    private void setPARENTCARDEID(){
        PARENTCARDEID = EID;
    }

    private void setSOURCE(){
        SOURCE = "External parameter";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EID).append(";").append(NAME).append(";")
                .append(SLOTTYPE).append(";")
                .append(SHELFEID).append(";")
                .append(PARENTCARDEID).append(";")
                .append(SOURCE).append(";");
        return sb.toString();
    }
}

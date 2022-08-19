package com.example.xmltask.Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class SlotMappingTable {
    @Id
    @Column(name = "eid")
    private String EID;
    @Column(name = "name")
    private String NAME;
    @Column(name = "slottype")
    private String SLOTTYPE;
    @Column(name = "shelfeid")
    private String SHELFEID;
    @Column(name = "parentcardeid")
    private String PARENTCARDEID;
    @Column(name = "source")
    private String SOURCE;

    public SlotMappingTable(Row row, String name) {
        setEID(row,name);
        setNAME(row);
        setSLOTTYPE();
        setSHELFEID(row, name);
        setPARENTCARDEID();
        setSOURCE();
    }

    public SlotMappingTable(String line) {
        String [] fields = line.split(";");
        setEID(fields[0]);
        setNAME(fields[1]);
        setSLOTTYPE(fields[2]);
        setSHELFEID(fields[3]);
        setPARENTCARDEID(fields[4]);
        setSOURCE(fields[5]);
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

    private void setEID(String value){
        EID = value;
    }

    private void setNAME(Row row){
        StringBuilder sb = new StringBuilder();
        sb.append("Slot-").append(row.getSlotPos())
                .append("/").append(row.getSlotNo());
        if(row.getSubSlotNo() != null)
            sb.append("/").append(row.getSubSlotNo());
        NAME = sb.toString();
    }

    private void setNAME(String value){
        NAME = value;
    }

    private void setSLOTTYPE(){
            SLOTTYPE = "SLOT";
    }

    private void setSLOTTYPE(String value) {
        SLOTTYPE = value;
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

    private void setSHELFEID(String value){
        SHELFEID = value;
    }

    private void setPARENTCARDEID(){
        PARENTCARDEID = EID;
    }

    private void setPARENTCARDEID(String value){
        PARENTCARDEID = value;
    }

    private void setSOURCE(){
        SOURCE = "External parameter";
    }

    private void setSOURCE(String value){
        SOURCE = value;
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

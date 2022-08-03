package com.example.xmltask.Service;

import com.example.xmltask.Models.Datapacket;
import com.example.xmltask.Models.Row;
import com.example.xmltask.Models.SlotMappingTable;
import com.example.xmltask.Models.Table;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class XmlToCsvService {
    public ResponseEntity parse(String body){
        List<String> dataForCsv = getDataForWriteToCsv(body);
        if(dataForCsv.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }
        Path source = Paths.get(System.getProperty("user.dir"));
        Path csvFileName = Paths.get(source.toAbsolutePath() + "/DataSlot.csv");
        if(!Files.exists(csvFileName)){
            try {
                Files.createFile(csvFileName);
                return writeDataToFile(csvFileName.toFile(), dataForCsv);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }else{
            return writeDataToFile(csvFileName.toFile(), dataForCsv);
        }
    }

    private ResponseEntity writeDataToFile(File csvFile, List<String> dataForCsv){
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(csvFile), "UTF-8"))){
            for(String element : dataForCsv){
                bw.write(element);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.created(csvFile.toURI()).build();
    }

    private List<String> getDataForWriteToCsv(String data) {
        XmlMapper xmlMapper = new XmlMapper();
        Datapacket datapacket = null;
        try {
            datapacket = xmlMapper.readValue(data, Datapacket.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<Table> tableSlot = datapacket.getTableList().stream().filter(obj -> obj.getAttrname().equals("Slot")).findFirst();
        String nameDevice = datapacket.getNe().getNEName();
        List<String> dataForCsv = new ArrayList<>();
        if (tableSlot.isPresent()) {
            List<Row> listRows = tableSlot.get().getRowList();
            StringBuilder namesColumns = new StringBuilder();
            SlotMappingTable slotMappingTable;
            Field[] fields = SlotMappingTable.class.getDeclaredFields();
            for(Field field : fields)
                namesColumns.append(field.getName()).append(";");
            dataForCsv.add(namesColumns.toString());
            for (int i = 0; i < listRows.size(); i++) {
                slotMappingTable = new SlotMappingTable(listRows.get(i),nameDevice);
                dataForCsv.add(slotMappingTable.toString());
            }
        }
        return dataForCsv;
    }
}

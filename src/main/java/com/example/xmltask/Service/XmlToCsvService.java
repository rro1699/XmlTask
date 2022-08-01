package com.example.xmltask.Service;

import com.example.xmltask.Models.Datapacket;
import com.example.xmltask.Models.Row;
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
            return ResponseEntity.badRequest().build();
        }
        Path source = Paths.get(System.getProperty("user.dir"));
        Path csvFileName = Paths.get(source.toAbsolutePath() + "/DataSlot.csv");
        if(!Files.exists(csvFileName)){
            try {
                Files.createFile(csvFileName);
                return writeDataToFile(csvFileName.toFile(), dataForCsv);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
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
            return ResponseEntity.badRequest().build();
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
        List<String> dataForCsv = new ArrayList<>();
        if (tableSlot.isPresent()) {
            List<Row> listRows = tableSlot.get().getRowList();
            StringBuilder namesColumns = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            Field[] fields;
            for (int i = 0; i < listRows.size(); i++) {
                sb.setLength(0);
                fields = listRows.get(i).getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.canAccess(listRows.get(i))) {
                        field.setAccessible(true);
                        Object fieldValue = null;
                        try {
                            fieldValue = field.get(listRows.get(i));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if (fieldValue != null) {
                            if (i == 0) {
                                namesColumns.append(field.getName()).append(";");
                            }
                            sb.append(fieldValue).append(";");
                        }
                    }
                }
                dataForCsv.add(sb.toString());
            }
            dataForCsv.add(0, namesColumns.toString());
        }
        return dataForCsv;
    }
}

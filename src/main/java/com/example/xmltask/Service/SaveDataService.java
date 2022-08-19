package com.example.xmltask.Service;

import com.example.xmltask.Models.SlotMappingTable;
import com.example.xmltask.Repository.DataFromCsvRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SaveDataService {
    private DataFromCsvRepository dataFromCsvRepository;

    public SaveDataService(DataFromCsvRepository dataFromCsvRepository) {
        this.dataFromCsvRepository = dataFromCsvRepository;
    }

    public ResponseEntity saveData(String path){
        Path csvFileName = Paths.get(path);
        try {
            List<String> dataLines = Files.readAllLines(csvFileName);
            dataLines.remove(0);
            for(String element : dataLines){
                dataFromCsvRepository.save(new SlotMappingTable(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAllLines(){
        return ResponseEntity.ok(dataFromCsvRepository.findAll());
    }
}

package com.example.xmltask.Repository;

import com.example.xmltask.Models.SlotMappingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFromCsvRepository extends JpaRepository<SlotMappingTable,String> {
}

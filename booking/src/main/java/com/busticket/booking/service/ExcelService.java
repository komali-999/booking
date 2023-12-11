package com.busticket.booking.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class ExcelService {
@Autowired
EmailService emailService;
    public void createExcel(Map<String, String> map) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("test");
            int rowno=0;
            for(HashMap.Entry entry:map.entrySet()) {

                HSSFRow row = sheet.createRow(rowno++);
                
                row.createCell(0).setCellValue((String)entry.getKey());
                row.createCell(1).setCellValue((String)entry.getValue());
            }
            FileOutputStream file = new FileOutputStream("FirstData.xls");
            wb.write(file);
            file.close();
            wb.close();
            System.out.println("Excel File has been created successfully.");
        } catch(Exception e){
            log.error("Error while creating excel", e);
        }
    }






}

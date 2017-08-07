package com.stefan.buchalter;

import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.domain.service.record.RecordService;
import com.stefan.buchalter.persistance.repositories.RecordRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        System.out.println("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/services.xml");

        RecordRepository recordRepository = (RecordRepository) context.getBean("recordRepository");
        RecordService recordService = (RecordService) context.getBean("recordService");

        Record pitRecord = new Record("Test PIT Record", LocalDate.now(), 1234.45);
        Record vatRecord = new Record("Test VAT Record", LocalDate.now(), 100.00, Record.VatRate.VAT_23, 1.0);


//        recordService.addExpenseRecord("R2016Q1M1", vatRecord);
//        recordService.addExpenseRecord("R2016Q1M1", pitRecord);

        List<Record> records = recordService.getAllExpenseRecordsForMReport("R2016Q1M1");
        System.out.println("END");
    }

}

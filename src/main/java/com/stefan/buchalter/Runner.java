package com.stefan.buchalter;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.persistance.RecordRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class Runner {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application.xml");

        RecordRepository recordRepository = (RecordRepository) context.getBean("recordRepository");

        Record record = new Record();

        record.setTitle("TEST");
        record.setDate(LocalDate.now());
        record.setNetValue(100.0);
        record.setType(Record.Type.VAT);
        record.setVatRate(Record.VatRate.VAT_23);
        record.setVatDeductionRate(1.0);
        record.recalculate();

        recordRepository.createExpenseRecord(1L, record);
    }

}

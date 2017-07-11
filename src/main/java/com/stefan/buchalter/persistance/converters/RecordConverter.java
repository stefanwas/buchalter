package com.stefan.buchalter.persistance.converters;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.persistance.model.PersistentRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RecordConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PersistentRecord convert(Record record, Long reportId) {
        PersistentRecord persistentRecord = new PersistentRecord();

        persistentRecord.setId(record.getId());
        persistentRecord.setReportId(reportId);
        persistentRecord.setType(record.getType().name());
        persistentRecord.setTitle(record.getTitle());
        persistentRecord.setDate(formatter.format(record.getDate()));

        // VAT
        persistentRecord.setNetValue(record.getNetValue());
        persistentRecord.setVatRate(record.getVatRate().name());
        persistentRecord.setVatValue(record.getVatValue());
        persistentRecord.setGrossValue(record.getGrossValue());
        persistentRecord.setVatDeductionRate(record.getVatDeductionRate());
        persistentRecord.setVatDeductionValue(record.getVatDeductionValue());

        // PIT
        persistentRecord.setPitValue(record.getPitValue());

        return persistentRecord;
    }


    public Record convert(PersistentRecord persistentRecord) {
        Record record = new Record();

        record.setId(persistentRecord.getId());
        record.setTitle(persistentRecord.getTitle());
        record.setType(Record.Type.valueOf(persistentRecord.getType()));
        record.setDate(LocalDate.parse(persistentRecord.getDate(), formatter));
        record.setPitValue(persistentRecord.getPitValue());
        record.setNetValue(persistentRecord.getNetValue());
        record.setVatRate(Record.VatRate.valueOf(persistentRecord.getVatRate()));
        record.setVatValue(persistentRecord.getVatValue());
        record.setGrossValue(persistentRecord.getGrossValue());
        record.setVatDeductionRate(persistentRecord.getVatDeductionRate());
        record.setVatDeductionValue(persistentRecord.getVatDeductionValue());

        return record;
    }
}

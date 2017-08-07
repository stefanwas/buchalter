package com.stefan.buchalter.domain.converters;

import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.persistance.model.PersistentRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RecordConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PersistentRecord convert(Record record, long reportId) {
        PersistentRecord persistentRecord = new PersistentRecord();

        persistentRecord.setId(record.getId());
        persistentRecord.setReportId(reportId);
        persistentRecord.setType(record.getType().name());
        persistentRecord.setTitle(record.getTitle());
        persistentRecord.setDate(formatter.format(record.getDate()));

        // VAT

        persistentRecord.setNetValue(record.getNetValue());
        persistentRecord.setVatRate(record.getVatRate() != null ? record.getVatRate().name() : null);
        persistentRecord.setVatValue(record.getVatValue());
        persistentRecord.setGrossValue(record.getGrossValue());
        persistentRecord.setVatDeductionRate(record.getVatDeductionRate());
        persistentRecord.setVatDeductionValue(record.getVatDeductionValue());

        // PIT
        persistentRecord.setPitValue(record.getPitValue());

        return persistentRecord;
    }


    public Record convert(PersistentRecord persistentRecord) {

        Record.Type type = Record.Type.valueOf(persistentRecord.getType());
        Record record = null;
        if (type == Record.Type.PIT) {
            record = new Record(
                    persistentRecord.getTitle(),
                    LocalDate.parse(persistentRecord.getDate(), formatter),
                    persistentRecord.getPitValue());
        }
        if (type == Record.Type.VAT) {
            record = new Record(
                    persistentRecord.getTitle(),
                    LocalDate.parse(persistentRecord.getDate(), formatter),
                    persistentRecord.getNetValue(),
                    Record.VatRate.valueOf(persistentRecord.getVatRate()),
                    persistentRecord.getVatDeductionRate());
        }

        record.setId(persistentRecord.getId());

        return record;
    }
}

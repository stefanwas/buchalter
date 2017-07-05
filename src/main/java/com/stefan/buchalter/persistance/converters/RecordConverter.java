package com.stefan.buchalter.persistance.converters;

import com.stefan.buchalter.domain.record.PitRecord;
import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.domain.record.VatRecord;
import com.stefan.buchalter.domain.report.Report;
import com.stefan.buchalter.persistance.model.PersistentRecord;

public class RecordConverter {

    public PersistentRecord convert(Record record, String reportCode) {
        PersistentRecord persistentRecord = new PersistentRecord();

        persistentRecord.setType(PersistentRecord.Type.valueOf(record.getType().name()));
        persistentRecord.setDate(record.getDate().toString()); //TODO use formatter
        persistentRecord.setTitle(record.getTitle());
        persistentRecord.setCode(reportCode);

        // PIT
        persistentRecord.setPitValue(((PitRecord) record).getPitValue());

        // VAT
        if (record.getType() == Record.Type.VAT) {
            persistentRecord.setNetValue(((VatRecord) record).getNetValue());
            persistentRecord.setVatValue(((VatRecord) record).getVatValue());
            persistentRecord.setVatRate(((VatRecord) record).getVatRate().name());
            persistentRecord.setGrossValue(((VatRecord) record).getGrossValue());
            persistentRecord.setVatDeductionRate(((VatRecord) record).getVatDeductionRate());
            persistentRecord.setVatDeductionValue(((VatRecord) record).getVatDeductionValue());
        }

        return persistentRecord;
    }


    public Record convert(PersistentRecord persistentRecord) {
        return null; //
    }
}

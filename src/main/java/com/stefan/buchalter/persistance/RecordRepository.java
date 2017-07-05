package com.stefan.buchalter.persistance;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.persistance.converters.RecordConverter;

import java.util.List;

public class RecordRepository {

    private RecordConverter converter;

    public Long createRecord(Long reportId, Record record) {
        if (record.getType() == Record.Type.PIT) {
            return createPitRecord(record);
        } else {
            return createVatRecord(record);
        }
    }

    public void updateRecord(Record record) {
        // TODO
    }

    private Long createVatRecord(Record record) {
        // TODO
        return 0L;
    }

    private Long createPitRecord(Record record) {
        // TODO
        return 0L;
    }

    public void deleteRecord(Long recordId) {
        // TODO
    }

    public List<Record> getAllRecordsForReport(Long reportId) {
        //TODO
        return null;
    }

    public void deleteAllRecordsForReport(Long reportId) {
        // TODO
    }




}

package com.stefan.buchalter.persistance;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.domain.report.Report;
import com.stefan.buchalter.persistance.converters.RecordConverter;
import com.stefan.buchalter.persistance.model.PersistentRecord;

import java.util.List;

public class ReportRepository {

    private RecordConverter converter;

    public Long createReport(Long parentRecordId, Report report) {
        // TODO
        return 0L;
    }

    public void updateRecord(Report report) {
        // TODO
    }

    public void deleteReport(Long reportId) {
        //TODO
    }

    public Record readReport(Long reportId) {
        //TODO
        return null;
    }

    public List<Record> getAllReportsForParentReport(Long parentReportId) {
        //TODO
        return null;
    }

    public void deleteAllReportsForParentReport(Long parentReportId) {
        // TODO
    }
}

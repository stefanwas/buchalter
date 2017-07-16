package com.stefan.buchalter.domain.service.reports;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.domain.service.record.RecordService;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;

public class MReportService {

    private FinanceBook book;
    private ReportConverter converter;
    private ReportRepository repository;
    private RecordService recordService;


    public void createMReport(String qReportCode, MReport mReport) {
        QReport qReport = book.getQReportByCode(qReportCode);

        PersistentReport persistentReport = converter.convert(mReport);
        Long mReportId = repository.createReport(persistentReport);
        mReport.setId(mReportId);

        qReport.addMReport(mReport);
    }

    public void deleteMReport(String qReportCode, String mRecordCode) {
        QReport qReport = book.getQReportByCode(qReportCode);
        MReport mReport = book.getMReportByCode(mRecordCode);

        recordService.removeAllIncomeRecordsForMReport(mRecordCode);
        recordService.removeAllExpenseRecordsForMReport(mRecordCode);

        repository.deleteReport(mReport.getId());

        qReport.removeMReport(mRecordCode);
    }

}

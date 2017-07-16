package com.stefan.buchalter.domain.service.reports;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;

public class QReportService {

    private FinanceBook book;
    private ReportConverter converter;
    private ReportRepository repository;
    private MReportService mReportService;

    public void createQReport(String yReportCode, QReport qReport) {
        YReport yReport = book.getYReportByCode(yReportCode);

        PersistentReport persistentReport = converter.convert(yReport);
        Long qReportId = repository.createReport(persistentReport);

        qReport.setId(qReportId);
        yReport.addQReport(qReport);
    }

    public void removeQReport(String yReportCode, String qRecordCode) {
        YReport yReport = book.getYReportByCode(yReportCode);
        QReport qReport = book.getQReportByCode(qRecordCode);

        qReport.getMReports().forEach(mReport -> mReportService.deleteMReport(qReport.getCode(), mReport.getCode()));

        repository.deleteReport(qReport.getId());
        yReport.removeQReport(qRecordCode);
    }
}

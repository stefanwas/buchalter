package com.stefan.buchalter.domain.service.reports;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;

public class YReportService {

    private FinanceBook financeBook;
    private ReportRepository repository;
    private ReportConverter converter;
    private QReportService qReportService;

    public void createYReport(int year) {
        YReport yReport = new YReport(year);

        PersistentReport persistentReport = converter.convert(yReport);
        Long yReportId = repository.createReport(persistentReport);
        yReport.setId(yReportId);

        financeBook.addAReport(yReport);
    }

    public void removeYReport(String yReportCode) {
        YReport yReport = financeBook.getYReportByCode(yReportCode);

        yReport.getQReports().forEach(qReport -> qReportService.removeQReport(yReportCode, qReport.getCode()));
        repository.deleteReport(yReport.getId());

        financeBook.removeYReport(yReportCode);
    }
}

package com.stefan.buchalter.domain.service.report;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YReportService {

    @Resource
    private FinanceBook financeBook;
    @Resource
    private ReportRepository repository;
    @Resource
    private ReportConverter converter;
    @Resource
    private QReportService qReportService;

    public List<String> getAllYReportCodes() {
        List<PersistentReport> persistentReports = repository.readAllReportByType("Y");
        List<String> yReportCodes = persistentReports.stream().map(PersistentReport::getCode).collect(Collectors.toList());
        return yReportCodes;
    }

    public void createYReport(YReport yReport) {
        PersistentReport persistentReport = converter.convert(yReport);
        Long yReportId = repository.createReport(persistentReport);
        yReport.setId(yReportId);

        financeBook.addYReport(yReport);
    }

    public YReport getYReport(String yReportCode) {
        PersistentReport persistentReport = repository.readReportByCode(yReportCode);
        YReport yReport = converter.convertToYReport(persistentReport);
        yReport.addAllQReports(qReportService.getAllQReportsForYReport(yReportCode));
        financeBook.addYReport(yReport);
        return yReport;
    }

    public void deleteYReport(String yReportCode) {
        YReport yReport = financeBook.getYReportByCode(yReportCode);

        yReport.getQReports().forEach(qReport -> qReportService.deleteQReport(yReportCode, qReport.getCode()));
        repository.deleteReport(yReport.getId());

        financeBook.removeYReport(yReportCode);
    }
}

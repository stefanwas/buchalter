package com.stefan.buchalter.domain.service.report;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.report.QReport;
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
    private ReportRepository repository;
    @Resource
    private ReportConverter converter;
    @Resource
    private QReportService qReportService;

    public List<String> getAllYReportCodes() {
        List<PersistentReport> persistentReports = repository.getAllReportsByType("Y");
        List<String> yReportCodes = persistentReports.stream().map(PersistentReport::getCode).collect(Collectors.toList());
        return yReportCodes;
    }

    public long createYReport(YReport yReport) {
        PersistentReport persistentReport = converter.convert(yReport);
        Long yReportId = repository.createReport(persistentReport);
        return  yReportId;
    }

    public YReport getYReportByCode(String yReportCode) {
        PersistentReport persistentReport = repository.getReportByCode(yReportCode);
        YReport yReport = converter.convertToYReport(persistentReport);
        List<QReport> qReports = qReportService.getAllQReportsForYReport(yReport.getId());
        yReport.addAllQReports(qReports);
        return yReport;
    }

    public YReport getYReportById(long yReportId) {
        PersistentReport persistentReport = repository.getReportById(yReportId);
        YReport yReport = converter.convertToYReport(persistentReport);
        yReport.addAllQReports(qReportService.getAllQReportsForYReport(yReportId));
        return yReport;
    }

    public void deleteYReportByCode(String yReportCode) {
        PersistentReport persistentYReport = repository.getReportByCode(yReportCode);
        List<PersistentReport> persistentQReports = repository.getAllQReportsForYReport(persistentYReport.getId());
        persistentQReports.forEach(qReport -> qReportService.deleteQReportById(qReport.getId()));
        repository.deleteReport(persistentYReport.getId());
    }

    public void deleteYReportById(long yReportId) {
        List<PersistentReport> persistentQReports = repository.getAllQReportsForYReport(yReportId);
        persistentQReports.forEach(qReport -> qReportService.deleteQReportById(qReport.getId()));
        repository.deleteReport(yReportId);

    }
}

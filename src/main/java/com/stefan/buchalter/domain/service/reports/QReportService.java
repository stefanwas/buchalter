package com.stefan.buchalter.domain.service.reports;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QReportService {

    @Resource
    private FinanceBook book;
    @Resource
    private ReportConverter converter;
    @Resource
    private ReportRepository repository;
    @Resource
    private MReportService mReportService;

    public void createQReport(String yReportCode, QReport qReport) {
        YReport yReport = book.getYReportByCode(yReportCode);

        PersistentReport persistentReport = converter.convert(qReport);
        Long qReportId = repository.createReport(persistentReport);

        qReport.setId(qReportId);
        yReport.addQReport(qReport);
    }

    public void deleteQReport(String yReportCode, String qRecordCode) {
        YReport yReport = book.getYReportByCode(yReportCode);
        QReport qReport = book.getQReportByCode(qRecordCode);

        qReport.getMReports().forEach(mReport -> mReportService.deleteMReport(qReport.getCode(), mReport.getCode()));

        repository.deleteReport(qReport.getId());
        yReport.removeQReport(qRecordCode);
    }

    public List<QReport> getAllQReportsForYReport(String yReportCode) {
        YReport yReport = book.getYReportByCode(yReportCode);
        Long yReportId = yReport.getId();
        List<PersistentReport> persistentReports = repository.getAllQReportsForYReport(yReportId);
        List<QReport> qReports = persistentReports.stream().map(converter::convertToQReport).collect(Collectors.toList());
        qReports.forEach(qReport -> qReport.addAllMReports(mReportService.getAllMReportsForQReport(qReport.getCode())));
        return qReports;
    }
}

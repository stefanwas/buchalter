package com.stefan.buchalter.domain.service.report;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.extractYCode;

@Service
public class QReportService {

    @Resource
    private ReportConverter converter;
    @Resource
    private ReportRepository repository;
    @Resource
    private MReportService mReportService;

    public long createQReport(QReport qReport) {
        String yReportCode = extractYCode(qReport.getCode());
        PersistentReport persistentYReport = repository.getReportByCode(yReportCode);
        PersistentReport persistentQReport = converter.convert(persistentYReport.getId(), qReport);
        Long qReportId = repository.createReport(persistentQReport);
        return qReportId;
    }

    public List<QReport> getAllQReportsForYReport(long yReportId) {
        List<PersistentReport> persistentReports = repository.getAllQReportsForYReport(yReportId);
        List<QReport> qReports = persistentReports.stream().map(converter::convertToQReport).collect(Collectors.toList());
        qReports.forEach(qReport -> qReport.addAllMReports(mReportService.getAllMReportsForQReport(qReport.getId())));
        return qReports;
    }

    public void deleteQReportById(long qReportId) {
        List<PersistentReport> mPersistentReports = repository.getAllMReportsForQReport(qReportId);
        mPersistentReports.forEach(mReport -> mReportService.deleteMReportById(mReport.getId()));
        repository.deleteReport(qReportId);
    }

    public void deleteQReportByCode(String qReportCode) {
        PersistentReport persistentQReport = repository.getReportByCode(qReportCode);
        List<PersistentReport> persistentMReports = repository.getAllMReportsForQReport(persistentQReport.getId());
        persistentMReports.forEach(mReport -> mReportService.deleteMReportById(mReport.getId()));
        repository.deleteReport(persistentQReport.getId());
    }
}

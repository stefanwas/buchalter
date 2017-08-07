package com.stefan.buchalter.domain.service.report;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.service.record.RecordService;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.extractYQCode;

@Service
public class MReportService {

    @Resource
    private ReportConverter converter;
    @Resource
    private ReportRepository repository;
    @Resource
    private RecordService recordService;


    public long createMReport(MReport mReport) {
        String qReportCode = extractYQCode(mReport.getCode());
        PersistentReport persistentQReport = repository.getReportByCode(qReportCode);
        PersistentReport persistentMReport = converter.convert(persistentQReport.getId(), mReport);
        Long mReportId = repository.createReport(persistentMReport);
        return mReportId;
    }

    public List<MReport> getAllMReportsForQReport(long qReportId) {
        List<PersistentReport> persistentMReports = repository.getAllMReportsForQReport(qReportId);
        List<MReport> mReports = persistentMReports.stream().map(converter::convertToMReport).collect(Collectors.toList());

        for (MReport mReport : mReports) {
            mReport.addAllIncomeRecords(recordService.getAllIncomeRecordsForMReport(mReport.getCode()));
            mReport.addAllExpenseRecords(recordService.getAllExpenseRecordsForMReport(mReport.getCode()));
        }

        return mReports;
    }

    public void deleteMReportById(long mReportId) {
        recordService.deleteAllIncomeRecordsForMReport(mReportId);
        recordService.deleteAllExpenseRecordsForMReport(mReportId);

        repository.deleteReport(mReportId);
    }

    public void deleteMReportByCode(String mReportCode) {
        PersistentReport persistentMReport = repository.getReportByCode(mReportCode);

        recordService.deleteAllIncomeRecordsForMReport(persistentMReport.getId());
        recordService.deleteAllExpenseRecordsForMReport(persistentMReport.getId());

        repository.deleteReport(persistentMReport.getId());
    }
}

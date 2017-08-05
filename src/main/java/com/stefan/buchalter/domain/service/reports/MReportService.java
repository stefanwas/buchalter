package com.stefan.buchalter.domain.service.reports;

import com.stefan.buchalter.domain.converters.ReportConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.service.record.RecordService;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MReportService {

    @Resource
    private FinanceBook book;
    @Resource
    private ReportConverter converter;
    @Resource
    private ReportRepository repository;
    @Resource
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

    public List<MReport> getAllMReportsForQReport(String qReportCode) {
        QReport qReport = book.getQReportByCode(qReportCode);
        Long qReportId = qReport.getId();
        List<PersistentReport> persistentReports = repository.getAllQReportsForYReport(qReportId);
        List<MReport> mReports = persistentReports.stream().map(converter::convertToMReport).collect(Collectors.toList());
        return mReports;
    }

}

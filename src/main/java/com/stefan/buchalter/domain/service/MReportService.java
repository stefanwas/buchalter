package com.stefan.buchalter.domain.service;

import com.stefan.buchalter.domain.Model;
import com.stefan.buchalter.domain.report.MReport;
import com.stefan.buchalter.domain.report.QReport;
import com.stefan.buchalter.persistance.ReportRepository;

public class MReportService {

    private Model model;
    private ReportRepository reportRepository;
    private RecordService recordService;


    public void createMReport(String qReportCode, MReport mReport) {
        QReport qReport = (QReport) model.getReportByCode(qReportCode);
        Long mReportId = reportRepository.createReport(qReport.getId(), mReport);

        mReport.setId(mReportId);
        qReport.addMReport(mReport);
    }

    public void deleteMReport(String qReportCode, String mRecordCode) {
        QReport qReport = (QReport) model.getReportByCode(qReportCode);
        MReport mReport = (MReport) model.getReportByCode(mRecordCode);

        recordService.removeAllIncomeRecords(mRecordCode);
        recordService.removeAllExpenseRecords(mRecordCode);

        reportRepository.deleteReport(mReport.getId());

        qReport.removeMReport(mRecordCode);
    }

    public void removeAllMReportsInQuarter(String qReportCode) {
        QReport qReport = (QReport) model.getReportByCode(qReportCode);
        qReport.getMReports().forEach(mReport -> deleteMReport(qReportCode, mReport.getCode()));
    }
}

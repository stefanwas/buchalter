package com.stefan.buchalter.domain.service;

import com.stefan.buchalter.domain.Model;
import com.stefan.buchalter.domain.report.AReport;
import com.stefan.buchalter.domain.report.QReport;
import com.stefan.buchalter.persistance.ReportRepository;

public class QReportService {

    private Model model;
    private ReportRepository reportRepository;
    private MReportService mReportService;

    public void createQReport(String aReportCode, QReport qReport) {
        AReport aReport = (AReport) model.getReportByCode(aReportCode);

        Long qReportId = reportRepository.createReport(aReport.getId(), qReport);

        qReport.setId(qReportId);
        aReport.addQReport(qReport);
    }

    public void removeQReport(String aReportCode, String qRecordCode) {
        AReport aReport = (AReport) model.getReportByCode(aReportCode);
        QReport qReport = (QReport) model.getReportByCode(qRecordCode);

        mReportService.removeAllMReportsInQuarter(qRecordCode);
        reportRepository.deleteReport(qReport.getId());
        aReport.removeQReport(qRecordCode);
    }
}

package com.stefan.buchalter.domain.service;

import com.stefan.buchalter.domain.Model;
import com.stefan.buchalter.domain.report.AReport;
import com.stefan.buchalter.domain.report.QReport;
import com.stefan.buchalter.persistance.ReportRepository;

public class AReportService {

    private Model model;
    private ReportRepository reportRepository;
    private MReportService qReportService;

    public void createAReport(int year) {
        AReport aReport = new AReport(year);
        Long aReportId = reportRepository.createReport(null, aReport);
        aReport.setId(aReportId);
        model.addReport(aReport.getCode(), aReport);
    }

    public void removeAReport(String aReportCode, String qRecordCode) {
        AReport aReport = (AReport) model.getReportByCode(aReportCode);
        QReport qReport = (QReport) model.getReportByCode(qRecordCode);

        qReportService.removeAllMReportsInQuarter(qRecordCode);
        reportRepository.deleteReport(qReport.getId());
        aReport.removeQReport(qRecordCode);
    }
}

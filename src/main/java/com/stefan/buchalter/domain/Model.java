package com.stefan.buchalter.domain;

import com.stefan.buchalter.domain.report.AReport;
import com.stefan.buchalter.domain.report.QReport;
import com.stefan.buchalter.domain.report.Report;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private Map<String, Report> reportsByCode = new HashMap<>();

    public Report getReportByCode(String code) {
        return reportsByCode.get(code);
    }

    public void addReport(String code, Report report) {
        reportsByCode.put(code, report);
    }
}

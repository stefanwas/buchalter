package com.stefan.buchalter.domain;

import com.stefan.buchalter.domain.report.AReport;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FinanceBook {

    private final LinkedHashMap<Integer, AReport> aReportsByYear = new LinkedHashMap<>();

    public List<Integer> getReportYears() {
        return new ArrayList<Integer>(aReportsByYear.keySet());
    }

    public AReport getAReportByYear(int year) {
        return aReportsByYear.get(year);
    }
}

package com.stefan.buchalter.domain.model;

import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class FinanceBook {

    private final LinkedHashMap<String, YReport> aReportsByCode = new LinkedHashMap<>();

    public void addYReport(YReport yReport) {
        aReportsByCode.put(yReport.getCode(), yReport);
    }

    public void removeYReport(String code) {
        aReportsByCode.remove(code);
    }

    public YReport getYReportByCode(String code) {
        return aReportsByCode.get(code);
    }

    public QReport getQReportByCode(String code) {
        YReport yReport = getYReportByCode(code.substring(0, 5));
        QReport qReport = yReport.getQReportByCode(code);
        return qReport;
    }

    public MReport getMReportByCode(String code) {
        QReport qReport = getQReportByCode(code.substring(0, 7));
        MReport mReport = qReport.getMReportByCode(code);
        return mReport;
    }
}

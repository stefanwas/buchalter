package com.stefan.buchalter.domain.converters;

import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import org.springframework.stereotype.Component;

/**
 * shallow converter - naturally converts only fields present in PersistentReport
 */
@Component
public class ReportConverter {

    public PersistentReport convert(YReport yReport) {
        PersistentReport persistentReport = new PersistentReport();
        persistentReport.setYear(yReport.getYear());
        persistentReport.setCode(yReport.getCode());
        persistentReport.setType("Y");
        return persistentReport;
    }

    public PersistentReport convert(long yReportId, QReport qReport) {
        PersistentReport persistentReport = new PersistentReport();
        persistentReport.setYReportId(yReportId);
        persistentReport.setYear(qReport.getYear());
        persistentReport.setQuarter(qReport.getQuarter());
        persistentReport.setCode(qReport.getCode());
        persistentReport.setType("Q");
        return persistentReport;
    }

    public PersistentReport convert(long qReportId, MReport mReport) {
        PersistentReport persistentReport = new PersistentReport();
        persistentReport.setQReportId(qReportId);
        persistentReport.setYear(mReport.getYear());
        persistentReport.setQuarter(mReport.getQuarter());
        persistentReport.setMonth(mReport.getMonth());
        persistentReport.setCode(mReport.getCode());
        persistentReport.setType("M");
        return persistentReport;
    }

    public YReport convertToYReport(PersistentReport persistentReport) {
        YReport yReport = new YReport(persistentReport.getYear());
        yReport.setId(persistentReport.getId());
        return yReport;
    }

    public QReport convertToQReport(PersistentReport persistentReport) {
        QReport qReport = new QReport(persistentReport.getYear(), persistentReport.getMonth());
        qReport.setId(persistentReport.getId());
        return qReport;

    }

    public MReport convertToMReport(PersistentReport persistentReport) {
        MReport mReport = new MReport(persistentReport.getYear(), persistentReport.getQuarter(), persistentReport.getMonth());
        mReport.setId(persistentReport.getId());
        return null;
    }
}

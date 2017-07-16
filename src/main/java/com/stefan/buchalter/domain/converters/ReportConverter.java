package com.stefan.buchalter.domain.converters;

import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.persistance.model.PersistentReport;
import org.springframework.stereotype.Component;

@Component
public class ReportConverter {

    // TODO implemente and add id from partent to method sigs
    public PersistentReport convert(YReport yReport) {
        return null;
    }

    public PersistentReport convert(QReport qReport) {
        return null;
    }

    public PersistentReport convert(MReport mReport) {
        return null;
    }

//    public
}

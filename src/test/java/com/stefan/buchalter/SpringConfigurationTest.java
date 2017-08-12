package com.stefan.buchalter;

import com.stefan.buchalter.domain.service.record.RecordService;
import com.stefan.buchalter.domain.service.report.MReportService;
import com.stefan.buchalter.domain.service.report.QReportService;
import com.stefan.buchalter.domain.service.report.YReportService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class SpringConfigurationTest {

    private String[] contextLocations = {
            "spring/datasource.xml",
            "spring/services.xml",
    };

    private ClassPathXmlApplicationContext springContext;


    @Before
    public void init() {
        springContext = new ClassPathXmlApplicationContext(contextLocations);
    }


    @Test
    public void testLoading() {
        RecordService recordService = (RecordService) springContext.getBean("recordService");
        assertNotNull(recordService);

        MReportService mReportService = (MReportService) springContext.getBean("MReportService");
        assertNotNull(mReportService);
        QReportService qReportService = (QReportService) springContext.getBean("QReportService");
        assertNotNull(qReportService);
        YReportService yReportService = (YReportService) springContext.getBean("YReportService");
        assertNotNull(yReportService);
    }
}

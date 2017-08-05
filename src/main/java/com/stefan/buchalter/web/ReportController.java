package com.stefan.buchalter.web;

import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.service.report.MReportService;
import com.stefan.buchalter.domain.service.report.QReportService;
import com.stefan.buchalter.domain.service.report.YReportService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYCode;
import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQCode;
import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQMCode;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Resource
    private YReportService yReportService;
    @Resource
    private QReportService qReportService;
    @Resource
    private MReportService mReportService;

    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<String> getAllAnnualReportCodes() {
        return yReportService.getAllYReportCodes();
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.PUT)
    public void createYReport(@PathVariable int year) {
        yReportService.createYReport(new YReport(year));
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.GET)
    public YReport getYReport(@PathVariable int year) {
        return yReportService.getYReport(createYCode(year));
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.DELETE)
    public void deleteYReport(@PathVariable int year) {
        yReportService.deleteYReport(createYCode(year));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.PUT)
    public void createQReport(@PathVariable int year, @PathVariable int quarter) {
        qReportService.createQReport(createYCode(year), new QReport(year, quarter));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.DELETE)
    public void deleteQReport(@PathVariable int year, @PathVariable int quarter) {
        qReportService.deleteQReport(createYCode(year), createYQCode(year, quarter));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.PUT)
    public void createMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        mReportService.createMReport(createYQCode(year, quarter), new MReport(year, quarter, month));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.DELETE)
    public void deleteMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        mReportService.deleteMReport(createYQCode(year, quarter), createYQMCode(year, quarter, month));
    }
}

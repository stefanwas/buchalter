package com.stefan.buchalter.web;

import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.service.report.MReportService;
import com.stefan.buchalter.domain.service.report.QReportService;
import com.stefan.buchalter.domain.service.report.YReportService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method= RequestMethod.PUT)
    public Long createReport(
            @RequestParam String type, @RequestParam Integer year, @RequestParam Integer quarter, @RequestParam Integer month) {

        if (type.equals("Y")) {
            return yReportService.createYReport(new YReport(year));
        }
        if (type.equals("Q")) {
            return qReportService.createQReport(new QReport(year, quarter));
        }
        if (type.equals("M")) {
            return mReportService.createMReport(new MReport(year, quarter, month));
        }

        return null;
    }


    @RequestMapping(value="/year/{year}", method= RequestMethod.PUT)
    public void createYReport(@PathVariable int year) {
        yReportService.createYReport(new YReport(year));
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.GET)
    public YReport getYReport(@PathVariable int year) {
        YReport yReport = yReportService.getYReportByCode(createYCode(year));
        return yReport;
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.DELETE)
    public void deleteYReport(@PathVariable int year) {
        yReportService.deleteYReportByCode(createYCode(year));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.PUT)
    public void createQReport(@PathVariable int year, @PathVariable int quarter) {
        qReportService.createQReport(new QReport(year, quarter));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.DELETE)
    public void deleteQReport(@PathVariable int year, @PathVariable int quarter) {
        qReportService.deleteQReportByCode(createYQCode(year, quarter));
    }

//    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.GET)
//    public MReport getMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
//        MReport mReport = mReportService.getMReportByCode(createYQMCode(year, quarter, month));
//        return mReport;
//    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.PUT)
    public void createMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        mReportService.createMReport(new MReport(year, quarter, month));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.DELETE)
    public void deleteMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        mReportService.deleteMReportByCode(createYQMCode(year, quarter, month));
    }
}

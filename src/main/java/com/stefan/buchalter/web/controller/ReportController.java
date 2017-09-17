package com.stefan.buchalter.web.controller;

import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.domain.model.report.QReport;
import com.stefan.buchalter.domain.model.report.YReport;
import com.stefan.buchalter.domain.service.report.MReportService;
import com.stefan.buchalter.domain.service.report.QReportService;
import com.stefan.buchalter.domain.service.report.YReportService;
import com.stefan.buchalter.web.validator.ReportRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYCode;
import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQCode;
import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQMCode;

@CrossOrigin
@RestController
@RequestMapping("/report")
public class ReportController {

//    private static final String ACCESS_CONTROL = "Access-Control-Allow-Origin";

    // Use web method PUT with id, without id - POST

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private YReportService yReportService;
    @Resource
    private QReportService qReportService;
    @Resource
    private MReportService mReportService;
    @Resource
    private ReportRequestValidator validator;



    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<String> getAllAnnualReportCodes() {
        LOG.info("Get all report codes.");
        return yReportService.getAllYReportCodes();
    }

    @RequestMapping(method= RequestMethod.POST)
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


    @RequestMapping(value="/year/{year}", method= RequestMethod.POST)
    public void createYReport(@PathVariable int year) {
        LOG.info("Create YReport - year={}", year);
        validator.validateY(year);
        yReportService.createYReport(new YReport(year));
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.GET)
    public YReport getYReport(@PathVariable int year) {
        LOG.info("Get YReport - year={}", year);
        YReport yReport = yReportService.getYReportByCode(createYCode(year));
        return yReport;
    }

    @RequestMapping(value="/year/{year}", method= RequestMethod.DELETE)
    public void deleteYReport(@PathVariable int year) {
        LOG.info("Delete YReport - year={}", year);
        yReportService.deleteYReportByCode(createYCode(year));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.POST)
    public void createQReport(@PathVariable int year, @PathVariable int quarter) {
        LOG.info("Create QReport - year={}, quarter={}", year, quarter);

        validator.validateYQ(year, quarter);
        qReportService.createQReport(new QReport(year, quarter));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}", method= RequestMethod.DELETE)
    public void deleteQReport(@PathVariable int year, @PathVariable int quarter) {
        LOG.info("Delete QReport - year={}, quarter={}", year, quarter);

        qReportService.deleteQReportByCode(createYQCode(year, quarter));
    }

//    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.GET)
//    public MReport getMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
//        MReport mReport = mReportService.getMReportByCode(createYQMCode(year, quarter, month));
//        return mReport;
//    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.POST)
    public void createMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        LOG.info("Create MReport - year={}, quarter={}, month={}", year, quarter, month);

        validator.validateYQM(year, quarter, month);
        mReportService.createMReport(new MReport(year, quarter, month));
    }

    @RequestMapping(value="/year/{year}/quarter/{quarter}/month/{month}", method= RequestMethod.DELETE)
    public void deleteMReport(@PathVariable int year, @PathVariable int quarter, @PathVariable int month) {
        LOG.info("Delete MReport - year={}, quarter={}, month={}", year, quarter, month);

        mReportService.deleteMReportByCode(createYQMCode(year, quarter, month));
    }
}

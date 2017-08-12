package com.stefan.buchalter.web.controller;

import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.domain.service.record.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
public class RecordController {

    private static final Logger LOG = LoggerFactory.getLogger(RecordController.class);

    @Resource
    private RecordService recordService;

    //TODO implement getExpenseReportByID

    @RequestMapping(value="/expense", method= RequestMethod.PUT)
    public long addExpenseRecord(@RequestParam String mReportCode, @RequestBody Record record) {
        LOG.info("Add Expense: {} to MReport {}", record, mReportCode);
        Long recordId = recordService.addExpenseRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/income", method= RequestMethod.PUT)
    public long addIncomeRecord(@RequestParam String mReportCode, @RequestBody Record record) {
        LOG.info("Add Income: {} to MReport {}", record, mReportCode);
        Long recordId = recordService.addIncomeRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/expense", method= RequestMethod.POST)
    public void updateExpenseReport(@RequestBody Record record) {
        LOG.info("Update Expense: {}", record);
        recordService.updateExpenseReport(record);
    }

    @RequestMapping(value="/income", method= RequestMethod.POST)
    public void updateIncomeReport(@RequestBody Record record) {
        LOG.info("Update Income: {}", record);
        recordService.updateIncomeReport(record);
    }

    @RequestMapping(value="/expense", method= RequestMethod.DELETE)
    public void deleteExpenseRecord(@RequestParam long recordId) {
        LOG.info("Delete Expense Record Id: {}", recordId);
        recordService.deleteExpenseRecord(recordId);
    }

    @RequestMapping(value="/income", method= RequestMethod.DELETE)
    public void deleteIncomeRecord(@RequestParam long recordId) {
        LOG.info("Delete Income Record Id: {}", recordId);
        recordService.deleteIncomeRecord(recordId);
    }

}

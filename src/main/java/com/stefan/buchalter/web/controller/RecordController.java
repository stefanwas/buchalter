package com.stefan.buchalter.web.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.domain.service.record.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    //TODO implement getExpenseReportByID

    @RequestMapping(value="/expense", method= RequestMethod.PUT)
    public long addExpenseRecord(@RequestParam String mReportCode, @RequestBody Record record) {
        Long recordId = recordService.addExpenseRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/income", method= RequestMethod.PUT)
    public long addIncomeRecord(@RequestParam String mReportCode, @RequestBody Record record) {
        Long recordId = recordService.addIncomeRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/expense", method= RequestMethod.POST)
    public void updateExpenseReport(@RequestBody Record record) {
        recordService.updateExpenseReport(record);
    }

    @RequestMapping(value="/income", method= RequestMethod.POST)
    public void updateIncomeReport(@RequestBody Record record) {
        recordService.updateIncomeReport(record);
    }

    @RequestMapping(value="/expense", method= RequestMethod.DELETE)
    public void deleteExpenseRecord(@RequestParam long recordId) {
        recordService.deleteExpenseRecord(recordId);
    }

    @RequestMapping(value="/income", method= RequestMethod.DELETE)
    public void deleteIncomeRecord(@RequestParam long recordId) {
        recordService.deleteIncomeRecord(recordId);
    }


}

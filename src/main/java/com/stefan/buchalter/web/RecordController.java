package com.stefan.buchalter.web;

import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.domain.service.record.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    //TODO implement getExpenseReportByID

    @RequestMapping(value="/expense", method= RequestMethod.PUT)
    public long addExpenseRecord(@RequestParam String mReportCode, Record record) {
        Long recordId = recordService.addExpenseRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/income", method= RequestMethod.PUT)
    public long addIncomeRecord(@RequestParam String mReportCode, Record record) {
        Long recordId = recordService.addIncomeRecord(mReportCode, record);
        return recordId;
    }

    @RequestMapping(value="/expense", method= RequestMethod.POST)
    public void updateExpenseReport(Record record) {
        recordService.updateExpenseReport(record);
    }

    @RequestMapping(value="/income", method= RequestMethod.POST)
    public void updateIncomeReport(Record record) {
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

package com.stefan.buchalter.domain.service;

import com.stefan.buchalter.domain.Model;
import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.domain.report.MReport;
import com.stefan.buchalter.persistance.RecordRepository;

public class RecordService {

    private Model model;
    private RecordRepository incomeRecordRepository;
    private RecordRepository expenseRecordRepository;

    public Record addIncomeRecord(String mReportCode, Record record) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        Long recordId = incomeRecordRepository.createRecord(mReport.getId(), record);
        record.setId(recordId);

        mReport.addIncomeRecord(record);
        return record;
    }

    public void removeIncomeRecord(String mReportCode, Long recordId) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        incomeRecordRepository.deleteRecord(recordId);
        mReport.removeIncomeRecord(recordId);
    }

    public void removeAllIncomeRecords(String mReportCode) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        incomeRecordRepository.deleteAllRecordsForReport(mReport.getId());
        mReport.removeAllIncomeRecords();
    }

    public Record addExpenseRecord(String mReportCode, Record record) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        Long recordId = expenseRecordRepository.createRecord(mReport.getId(), record);
        record.setId(recordId);

        mReport.addExpenseRecord(record);
        return record;
    }

    public void removeExpenseRecord(String mReportCode, Long recordId) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        expenseRecordRepository.deleteRecord(recordId);
        mReport.removeExpenseRecord(recordId);
    }

    public void removeAllExpenseRecords(String mReportCode) {
        MReport mReport = (MReport) model.getReportByCode(mReportCode);

        expenseRecordRepository.deleteAllRecordsForReport(mReport.getId());
        mReport.removeAllExpenseRecords();
    }
}

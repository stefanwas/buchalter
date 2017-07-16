package com.stefan.buchalter.domain.service.record;

import com.stefan.buchalter.domain.converters.RecordConverter;
import com.stefan.buchalter.domain.model.FinanceBook;
import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.domain.model.report.MReport;
import com.stefan.buchalter.persistance.model.PersistentRecord;
import com.stefan.buchalter.persistance.repositories.RecordRepository;

public class RecordService {

    private FinanceBook book;
    private RecordConverter converter;
    private RecordRepository repository;

    public Record addIncomeRecord(String mReportCode, Record record) {
        MReport mReport = book.getMReportByCode(mReportCode);

        PersistentRecord persistentRecord = converter.convert(record, mReport.getId());

        Long recordId = repository.createIncomeRecord(persistentRecord);
        record.setId(recordId);

        mReport.addIncomeRecord(record);
        return record;
    }

    public void removeIncomeRecord(String mReportCode, Long recordId) {
        MReport mReport = book.getMReportByCode(mReportCode);

        repository.deleteIncomeRecord(recordId);
        mReport.removeIncomeRecord(recordId);
    }

    public void removeAllIncomeRecordsForMReport(String mReportCode) {
        MReport mReport = book.getMReportByCode(mReportCode);

        repository.deleteAllIncomeRecordsForReport(mReport.getId());

        mReport.removeAllIncomeRecords();
    }

    public Record addExpenseRecord(String mReportCode, Record record) {
        MReport mReport = book.getMReportByCode(mReportCode);

        PersistentRecord persistentRecord = converter.convert(record, mReport.getId());

        Long recordId = repository.createExpenseRecord(persistentRecord);
        record.setId(recordId);

        mReport.addIncomeRecord(record);
        return record;
    }

    public void removeExpenseRecord(String mReportCode, Long recordId) {
        MReport mReport = book.getMReportByCode(mReportCode);

        repository.deleteExpenseRecord(recordId);
        mReport.removeIncomeRecord(recordId);
    }

    public void removeAllExpenseRecordsForMReport(String mReportCode) {
        MReport mReport = book.getMReportByCode(mReportCode);

        repository.deleteAllExpenseRecordsForReport(mReport.getId());

        mReport.removeAllIncomeRecords();
    }


}

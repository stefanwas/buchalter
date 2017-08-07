package com.stefan.buchalter.domain.service.record;

import com.stefan.buchalter.domain.converters.RecordConverter;
import com.stefan.buchalter.domain.model.record.Record;
import com.stefan.buchalter.persistance.model.PersistentRecord;
import com.stefan.buchalter.persistance.model.PersistentReport;
import com.stefan.buchalter.persistance.repositories.RecordRepository;
import com.stefan.buchalter.persistance.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Quite consistent - uses only record ids and report codes
 */
@Service
public class RecordService {

    @Resource
    private RecordConverter converter;
    @Resource
    private RecordRepository recordRepository;
    @Resource
    private ReportRepository reportRepository;

    /*** CREATE ***/

    public long addIncomeRecord(String mReportCode, Record record) {
        PersistentReport persistentReport = reportRepository.getReportByCode(mReportCode);
        PersistentRecord persistentRecord = converter.convert(record, persistentReport.getId());

        Long recordId = recordRepository.createIncomeRecord(persistentRecord);
        return recordId;
    }

    public long addExpenseRecord(String mReportCode, Record record) {
        PersistentReport persistentReport = reportRepository.getReportByCode(mReportCode);
        PersistentRecord persistentRecord = converter.convert(record, persistentReport.getId());

        Long recordId = recordRepository.createExpenseRecord(persistentRecord);
        return recordId;
    }

    /*** GET ***/

    public Record getIncomeRecord(long recordId) {
        PersistentRecord persistentRecord = recordRepository.getIncomeRecord(recordId);
        Record record = converter.convert(persistentRecord);
        return record;
    }

    public Record getExpenseRecord(long recordId) {
        PersistentRecord persistentRecord = recordRepository.getExpenseRecord(recordId);
        Record record = converter.convert(persistentRecord);
        return record;
    }

    public List<Record> getAllIncomeRecordsForMReport(String mReportCode) {
        PersistentReport persistentReport = reportRepository.getReportByCode(mReportCode);
        List<PersistentRecord> persistentRecords = recordRepository.getAllIncomeRecordsForReport(persistentReport.getId());
        List<Record> records = persistentRecords.stream().map(converter::convert).collect(Collectors.toList());
        return records;
    }

    public List<Record> getAllExpenseRecordsForMReport(String mReportCode) {
        PersistentReport persistentReport = reportRepository.getReportByCode(mReportCode);
        List<PersistentRecord> persistentRecords = recordRepository.getAllExpenseRecordsForReport(persistentReport.getId());
        List<Record> records = persistentRecords.stream().map(converter::convert).collect(Collectors.toList());
        return records;
    }

    /*** UPDATE ***/

    public void updateIncomeReport(Record record) {
        PersistentRecord persistentRecord = converter.convert(record, 0L);
        recordRepository.updateIncomeRecord(persistentRecord);
    }

    public void updateExpenseReport(Record record) {
        PersistentRecord persistentRecord = converter.convert(record, 0L);
        recordRepository.updateExpenseRecord(persistentRecord);
    }

    /*** DELETE ***/

    public void deleteIncomeRecord(long recordId) {
        recordRepository.deleteIncomeRecord(recordId);
    }

    public void deleteAllIncomeRecordsForMReport(long mReportId) {
        recordRepository.deleteAllIncomeRecordsForReport(mReportId);
    }

    public void deleteExpenseRecord(long recordId) {
        recordRepository.deleteExpenseRecord(recordId);
    }

    public void deleteAllExpenseRecordsForMReport(long mReportId) {
        recordRepository.deleteAllExpenseRecordsForReport(mReportId);
    }

}

package com.stefan.buchalter.persistance;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.persistance.converters.RecordConverter;
import com.stefan.buchalter.persistance.model.PersistentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecordRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordRepository.class);

    private static final String EXPENSE_RECORDS = "expense_records";
    private static final String INCOME_RECORDS = "income_records";

    @Resource
    private RecordConverter converter;
    @Resource
    private JdbcTemplate jdbcTemplate;

    private RowMapper recordMapper = new RowMapper() {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) {
            return null;
        }
    };

    public long createExpenseRecord(Long reportId, Record record) {
        long id = createRecord(reportId, record, EXPENSE_RECORDS);
        return id;
    }

    public long createIncomeRecord(Long reportId, Record record) {
        long id = createRecord(reportId, record, INCOME_RECORDS);
        return id;
    }

    private long createRecord(Long reportId, Record record, String table) {

        PersistentRecord persistentRecord = converter.convert(record, reportId);

        return jdbcTemplate.update(
                "INSERT INTO ? (" +
                        " report_id," +
                        " date," +
                        " type," +
                        " title," +
                        " pit_value," +
                        " net_value," +
                        " vat_rate," +
                        " vat_value," +
                        " gross_value," +
                        " vat_deduct_rate," +
                        " vat_deduct_value)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                table,
                persistentRecord.getReportId(),
                persistentRecord.getDate(),
                persistentRecord.getType(),
                persistentRecord.getTitle(),
                persistentRecord.getPitValue(),
                persistentRecord.getNetValue(),
                persistentRecord.getVatRate(),
                persistentRecord.getVatValue(),
                persistentRecord.getGrossValue(),
                persistentRecord.getVatDeductionRate(),
                persistentRecord.getVatDeductionValue());
    }

    public void updateExpenseRecord(Long reportId, Record record) {
        updateRecord(reportId, record, EXPENSE_RECORDS);
    }

    public void updateIncomeRecord(Long reportId, Record record) {
        updateRecord(reportId, record, INCOME_RECORDS);
    }

    private void updateRecord(Long reportId, Record record, String tableName) {
        PersistentRecord persistentRecord = converter.convert(record, reportId);

        int id = jdbcTemplate.update(
                "UPDATE ? SET" +
                " id = ?," +
                " report_id = ?," +
                " date = ?," +
                " type = ?," +
                " title = ?," +
                " pit_value = ?," +
                " net_value = ?," +
                " vat_rate = ?," +
                " vat_value = ?," +
                " gross_value = ?," +
                " vat_deduct_rate = ?," +
                " vat_deduct_value = ?)",
                tableName,
                persistentRecord.getId(),
                persistentRecord.getReportId(),
                persistentRecord.getDate(),
                persistentRecord.getType(),
                persistentRecord.getTitle(),
                persistentRecord.getPitValue(),
                persistentRecord.getNetValue(),
                persistentRecord.getVatRate(),
                persistentRecord.getVatValue(),
                persistentRecord.getGrossValue(),
                persistentRecord.getVatDeductionRate(),
                persistentRecord.getVatDeductionValue());
    }


    public void deleteExpenseRecord(long recordId) {
        deleteRecord(recordId, EXPENSE_RECORDS);
    }

    public void deleteIncomeRecord(long recordId) {
        deleteRecord(recordId, INCOME_RECORDS);
    }

    private void deleteRecord(long recordId, String tableName) {
        jdbcTemplate.update("DELETE FROM ? WHERE id = ?", tableName, recordId);
    }

    public List<Record> getAllExpenseRecordsForReport(long reportId) {
        List<Record> records = getAllRecordsForReport(reportId, EXPENSE_RECORDS);
        return records;
    }

    public List<Record> getAllIncomeRecordsForReport(long reportId) {
        List<Record> records = getAllRecordsForReport(reportId, INCOME_RECORDS);
        return records;
    }

    private List<Record> getAllRecordsForReport(long reportId, String tableName) {
        String query = "SELECT" +
                " report_id," +
                " id," +
                " date," +
                " tape," +
                " title," +
                " pit_value," +
                " net_value," +
                " vat_rate," +
                " vat_value," +
                " gross_value," +
                " vat_deduct_rate," +
                " vat_deduct_value" +
                " FROM ?" +
                " WHERE report_id = ?";
        return jdbcTemplate.query(query, new Object[] {tableName, reportId}, recordMapper);

    }

    public void deleteAllExpenseRecordsForReport(long reportId) {
        deleteAllRecordsForReport(reportId, EXPENSE_RECORDS);
    }

    public void deleteAllIncomeRecordsForReport(long reportId) {
        deleteAllRecordsForReport(reportId, INCOME_RECORDS);
    }

    private void deleteAllRecordsForReport(long reportId, String tableName) {
        jdbcTemplate.update("DELETE FROM ? WHERE report_id = ?", tableName, reportId);
    }

}

class RecordMapper implements RowMapper<PersistentRecord> {
    @Override
    public PersistentRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PersistentRecord record = new PersistentRecord();

        record.setId(resultSet.getLong("id"));
        record.setReportId(resultSet.getLong("report_id"));
        record.setDate(resultSet.getString("date"));
        record.setTitle(resultSet.getString("title"));
        record.setPitValue(resultSet.getDouble("pit_value"));
        record.setNetValue(resultSet.getDouble("net_value"));
        record.setVatRate(resultSet.getString("vat_rate"));
        record.setVatValue(resultSet.getDouble("vat_value"));
        record.setGrossValue(resultSet.getDouble("gross_value"));
        record.setVatDeductionRate(resultSet.getDouble("vat_deduct_rate"));
        record.setVatDeductionValue(resultSet.getDouble("vat_deduct_value"));

        return record;
    }
}
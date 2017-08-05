package com.stefan.buchalter.persistance.repositories;

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
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<PersistentRecord> mapper = new RowMapper<PersistentRecord>() {

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
    };

    public long createExpenseRecord(PersistentRecord persistentRecord) {
        long id = createRecord(persistentRecord, EXPENSE_RECORDS);
        return id;
    }

    public long createIncomeRecord(PersistentRecord persistentRecord) {
        long id = createRecord(persistentRecord, INCOME_RECORDS);
        return id;
    }

    // TODO returns nb of rows instead of id !!! fix it
    private long createRecord(PersistentRecord persistentRecord, String table) {
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

    public void updateExpenseRecord(Long reportId, PersistentRecord persistentRecord) {
        updateRecord(reportId, persistentRecord, EXPENSE_RECORDS);
    }

    public void updateIncomeRecord(Long reportId, PersistentRecord persistentRecord) {
        updateRecord(reportId, persistentRecord, INCOME_RECORDS);
    }

    private void updateRecord(Long reportId, PersistentRecord persistentRecord, String tableName) {
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

    public List<PersistentRecord> getAllExpenseRecordsForReport(long reportId) {
        List<PersistentRecord> records = getAllRecordsForReport(reportId, EXPENSE_RECORDS);
        return records;
    }

    public List<PersistentRecord> getAllIncomeRecordsForReport(long reportId) {
        List<PersistentRecord> records = getAllRecordsForReport(reportId, INCOME_RECORDS);
        return records;
    }

    private List<PersistentRecord> getAllRecordsForReport(long reportId, String tableName) {
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

        return jdbcTemplate.query(query, new Object[]{tableName, reportId}, mapper);
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


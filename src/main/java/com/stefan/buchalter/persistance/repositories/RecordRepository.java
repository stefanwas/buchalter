package com.stefan.buchalter.persistance.repositories;

import com.stefan.buchalter.persistance.model.PersistentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordRepository.class);

    private static final String EXPENSE_RECORDS_TABLE = "expense_records";
    private static final String INCOME_RECORDS_TABLE = "income_records";

    private static final String[] ALL_COLUMNS = new String[] {
            "id",
            "report_id",
            "type",
            "date",
            "title",
            "net_value",
            "vat_rate",
            "vat_value",
            "gross_value",
            "pit_value",
            "vat_deduct_rate",
            "vat_deduct_value" };


    @Resource
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<PersistentRecord> mapper = new RowMapper<PersistentRecord>() {

        @Override
        public PersistentRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            PersistentRecord record = new PersistentRecord();

            record.setId(resultSet.getLong("id"));
            record.setReportId(resultSet.getLong("report_id"));
            record.setType(resultSet.getString("type"));
            record.setDate(resultSet.getString("date"));
            record.setTitle(resultSet.getString("title"));
            record.setNetValue(resultSet.getDouble("net_value"));
            record.setVatRate(resultSet.getString("vat_rate"));
            record.setVatValue(resultSet.getDouble("vat_value"));
            record.setGrossValue(resultSet.getDouble("gross_value"));
            record.setPitValue(resultSet.getDouble("pit_value"));
            record.setVatDeductionRate(resultSet.getDouble("vat_deduct_rate"));
            record.setVatDeductionValue(resultSet.getDouble("vat_deduct_value"));

            return record;
        }
    };

    /*** CREATE ***/

    public long createExpenseRecord(PersistentRecord persistentRecord) {
        long id = createRecord(persistentRecord, EXPENSE_RECORDS_TABLE);
        return id;
    }

    public long createIncomeRecord(PersistentRecord persistentRecord) {
        long id = createRecord(persistentRecord, INCOME_RECORDS_TABLE);
        return id;
    }

    private long createRecord(PersistentRecord persistentRecord, String table) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String[] columnsToInsert = Arrays.copyOfRange(ALL_COLUMNS, 1, ALL_COLUMNS.length);
                String insertStatement =
                        "INSERT INTO " + table + "(" + String.join(", ", columnsToInsert) + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(insertStatement, new String[] {"id"});

                ps.setLong(1, persistentRecord.getReportId());
                ps.setString(2, persistentRecord.getType());
                ps.setString(3, persistentRecord.getDate());
                ps.setString(4, persistentRecord.getTitle());
                ps.setDouble(5, persistentRecord.getNetValue());
                ps.setString(6, persistentRecord.getVatRate());
                ps.setDouble(7, persistentRecord.getVatValue());
                ps.setDouble(8, persistentRecord.getGrossValue());
                ps.setDouble(9, persistentRecord.getPitValue());
                ps.setDouble(10, persistentRecord.getVatDeductionRate());
                ps.setDouble(11, persistentRecord.getVatDeductionValue());

                return ps;
            }
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /*** GET ***/

    public PersistentRecord getExpenseRecord(long recordId) {
        PersistentRecord record = getRecord(recordId, EXPENSE_RECORDS_TABLE);
        return record;
    }

    public PersistentRecord getIncomeRecord(long recordId) {
        PersistentRecord record = getRecord(recordId, INCOME_RECORDS_TABLE);
        return record;
    }

    private PersistentRecord getRecord(long recordId, String tableName) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM " + tableName + " WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[] {recordId}, mapper);
    }

    public List<PersistentRecord> getAllExpenseRecordsForReport(long reportId) {
        List<PersistentRecord> records = getAllRecordsForReport(reportId, EXPENSE_RECORDS_TABLE);
        return records;
    }

    public List<PersistentRecord> getAllIncomeRecordsForReport(long reportId) {
        List<PersistentRecord> records = getAllRecordsForReport(reportId, INCOME_RECORDS_TABLE);
        return records;
    }

    private List<PersistentRecord> getAllRecordsForReport(long reportId, String tableName) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM " + tableName + " WHERE report_id = ?";
        return jdbcTemplate.query(query, new Object[] {reportId}, mapper);
    }

    /*** UPDATE ***/

    public void updateExpenseRecord(PersistentRecord persistentRecord) {
        updateRecord(persistentRecord, EXPENSE_RECORDS_TABLE);
    }

    public void updateIncomeRecord(PersistentRecord persistentRecord) {
        updateRecord(persistentRecord, INCOME_RECORDS_TABLE);
    }

    private void updateRecord(PersistentRecord persistentRecord, String tableName) {
        String[] columnsToUpdate = Arrays.copyOfRange(ALL_COLUMNS, 2, ALL_COLUMNS.length);
        int id = jdbcTemplate.update(
                "UPDATE " + tableName + " SET " +
                " type = ?," +
                " date = ?," +
                " title = ?," +
                " net_value = ?," +
                " vat_rate = ?," +
                " vat_value = ?," +
                " gross_value = ?," +
                " pit_value = ?," +
                " vat_deduct_rate = ?," +
                " vat_deduct_value = ?" +
                " where id = ?",
                persistentRecord.getType(),
                persistentRecord.getDate(),
                persistentRecord.getTitle(),
                persistentRecord.getNetValue(),
                persistentRecord.getVatRate(),
                persistentRecord.getVatValue(),
                persistentRecord.getGrossValue(),
                persistentRecord.getPitValue(),
                persistentRecord.getVatDeductionRate(),
                persistentRecord.getVatDeductionValue(),
                persistentRecord.getId());
    }

    /*** DELETE ***/

    public void deleteExpenseRecord(long recordId) {
        deleteRecord(recordId, EXPENSE_RECORDS_TABLE);
    }

    public void deleteIncomeRecord(long recordId) {
        deleteRecord(recordId, INCOME_RECORDS_TABLE);
    }

    private void deleteRecord(long recordId, String tableName) {
        jdbcTemplate.update("DELETE FROM " + tableName + " WHERE id = ?", recordId);
    }

    public void deleteAllExpenseRecordsForReport(long reportId) {
        deleteAllRecordsForReport(reportId, EXPENSE_RECORDS_TABLE);
    }

    public void deleteAllIncomeRecordsForReport(long reportId) {
        deleteAllRecordsForReport(reportId, INCOME_RECORDS_TABLE);
    }

    private void deleteAllRecordsForReport(long reportId, String tableName) {
        jdbcTemplate.update("DELETE FROM " + tableName +" WHERE report_id = ?", reportId);
    }

}


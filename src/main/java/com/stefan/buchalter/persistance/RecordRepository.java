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

    @Resource
    private RecordConverter converter;
    @Resource
    private JdbcTemplate jdbcTemplate;

    private RecordMapper recordMapper = new RecordMapper();




    public long createRecord(Long reportId, Record record) {

        PersistentRecord persistentRecord = converter.convert(record, reportId);

        int id = jdbcTemplate.update(
                "INSERT INTO records (" +
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

        return id;
    }

    public void updateRecord(Long reportId, Record record) {
        PersistentRecord persistentRecord = converter.convert(record, reportId);

        int id = jdbcTemplate.update(
                "UPDATE records SET" +
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


    public void deleteRecord(Long recordId) {
        jdbcTemplate.update("DELETE FROM records WHERE id = ?", recordId);
    }

    public List<Record> getAllRecordsForReport(Long reportId) {
        //TODO
        return null;
    }

    public void deleteAllRecordsForReport(Long reportId) {
        // TODO
    }



}

class RecordMapper implements RowMapper<PersistentRecord> {
    @Override
    public PersistentRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PersistentRecord record = new PersistentRecord();

//        record.setId(resultSet.getLong("id"));
//        record.setUsername(resultSet.getString("username"));
//        record.setPassword(resultSet.getString("password"));
//        record.setEncryptedPassword(resultSet.getString("encrypted_password"));
//        record.setRoles(resultSet.getString("roles"));

        return record;
    }
}
package com.stefan.buchalter.persistance.repositories;

import com.stefan.buchalter.persistance.model.PersistentReport;
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
public class ReportRepository {

    private static final String[] ALL_COLUMNS = new String[] {"id", "y_report_id", "q_report_id", "type", "code", "year", "quarter", "month"};

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<PersistentReport> mapper = new RowMapper<PersistentReport>() {
        @Override
        public PersistentReport mapRow(ResultSet rs, int rowNum) throws SQLException {

            PersistentReport persistentReport = new PersistentReport();
            persistentReport.setId(rs.getLong("id"));
            persistentReport.setYReportId(rs.getLong("y_report_id"));
            persistentReport.setQReportId(rs.getLong("q_report_id"));
            persistentReport.setType(rs.getString("type"));
            persistentReport.setCode(rs.getString("code"));
            persistentReport.setYear(rs.getInt("year"));
            persistentReport.setQuarter(rs.getInt("quarter"));
            persistentReport.setMonth(rs.getInt("month"));

            return persistentReport;
        }
    };

    /*** CREATE ***/

    public long createReport(PersistentReport persistentReport) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String[] columnsToInsert = Arrays.copyOfRange(ALL_COLUMNS, 1, ALL_COLUMNS.length);
                PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO reports (" + String.join(", ", columnsToInsert) + ") values (?, ?, ?, ?, ?, ?, ?)", new String[] {"id"});

                ps.setLong(1, persistentReport.getYReportId() != null ? persistentReport.getYReportId() : 0);
                ps.setLong(2, persistentReport.getQReportId() != null ? persistentReport.getQReportId() : 0);
                ps.setString(3, persistentReport.getType());
                ps.setString(4, persistentReport.getCode());
                ps.setInt(5, persistentReport.getYear());
                ps.setInt(6, persistentReport.getQuarter());
                ps.setInt(7, persistentReport.getMonth());

                return ps;
            }
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /*** GET ***/

    public List<PersistentReport> getAllReportsByType(String type) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM reports WHERE type = ?";
        List<PersistentReport> persistentReports = jdbcTemplate.query(query, new Object[] {type}, mapper);
        return persistentReports;
    }

    public PersistentReport getReportById(long reportId) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM reports WHERE id = ?";
        PersistentReport persistentReport = jdbcTemplate.queryForObject(query, new Object[]{reportId}, mapper);
        return persistentReport;
    }

    public PersistentReport getReportByCode(String yReportCode) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM reports WHERE code = ?";
        PersistentReport persistentReport = jdbcTemplate.queryForObject(query, new Object[]{yReportCode}, mapper);
        return persistentReport;
    }

    public List<PersistentReport> getAllQReportsForYReport(long yReportId) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM reports WHERE y_report_id = ? and type = 'Q'";
        List<PersistentReport> persistentReport = jdbcTemplate.query(query, new Object[] {yReportId}, mapper);
        return persistentReport;
    }

    public List<PersistentReport> getAllMReportsForQReport(long qReportId) {
        String query = "SELECT " + String.join(", ", ALL_COLUMNS) + " FROM reports WHERE q_report_id = ? and type = 'M'";
        List<PersistentReport> persistentReport = jdbcTemplate.query(query, new Object[] {qReportId}, mapper);
        return persistentReport;
    }

    /*** DELETE ***/

    public void deleteReport(long reportId) {
        jdbcTemplate.update("DELETE FROM report where id = ?", reportId);
    }

}

package com.stefan.buchalter.persistance.repositories;

import com.stefan.buchalter.domain.converters.ReportConverter;
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
import java.util.List;

@Repository
public class ReportRepository {

    @Resource
    private ReportConverter converter;
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
            persistentReport.setCode(rs.getString("year"));
            persistentReport.setCode(rs.getString("quarter"));
            persistentReport.setCode(rs.getString("month"));

            return persistentReport;
        }
    };

    public long createReport(PersistentReport report) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO reports (y_report_id, q_report_id, type, code, year, quarter, month) values (?, ?, ?, ?, ?, ?, ?)",
                        new String[] {"id"});
                ps.setLong(1, report.getYReportId());
                ps.setLong(2, report.getQReportId());
                ps.setString(3, report.getType());
                ps.setString(4, report.getCode());
                ps.setInt(5, report.getYear());
                ps.setInt(6, report.getQuarter());
                ps.setInt(7, report.getMonth());
                return ps;
            }
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public PersistentReport readReport(long reportId) {
        String query = "SELECT id, y_report_id, q_report_id, type, code, year, quarter, month FROM reports WHERE id = ?";
        PersistentReport persistentReport = jdbcTemplate.queryForObject(query, new Object[]{reportId}, mapper);
        return persistentReport;
    }

    public List<PersistentReport> getAllReportsForYear(int year) {
        String query = "SELECT id, y_report_id, q_report_id, type, code, year, quarter, month FROM reports WHERE year = ?";
        List<PersistentReport> persistentReport = jdbcTemplate.query(query, new Object[]{year}, mapper);
        return persistentReport;
    }

    public void deleteReport(long reportId) {
        jdbcTemplate.update("DELETE FROM reports where id = ?", reportId);
    }

    public void deleteMReport(long mReportId) {
        jdbcTemplate.update("DELETE FROM reports where id = ? and type = 'M", mReportId);
    }

//    public void deleteQReportWithAllSubReports(long qReportId) {
//        jdbcTemplate.update("DELETE FROM reports where id = ? and type = 'Q", qReportId);
//        jdbcTemplate.update("DELETE FROM reports where q_report_id = ? and type = 'M", qReportId);
//    }
//
//    public void deleteYReportWithAllSubReports(long yReportId) {
//        jdbcTemplate.update("DELETE FROM reports where id = ?", yReportId);
//        jdbcTemplate.update("DELETE FROM reports where y_report_id = ? and type in ('Q', 'M')", yReportId);
//    }

}

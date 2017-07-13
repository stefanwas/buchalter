package com.stefan.buchalter.persistance;

import com.stefan.buchalter.domain.record.Record;
import com.stefan.buchalter.domain.report.AReport;
import com.stefan.buchalter.domain.report.Report;
import com.stefan.buchalter.persistance.converters.RecordConverter;
import com.stefan.buchalter.persistance.converters.ReportConverter;
import com.stefan.buchalter.persistance.model.PersistentRecord;
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

    private RowMapper<PersistentReport> mapper = new RowMapper<PersistentReport>() {
        @Override
        public PersistentReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }
    };

    public long createAReport(AReport aReport) {
        PersistentReport persistentReport = converter.convert(aReport);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(
                                "INSERT INTO reports (code, year) values (?, ?)", new String[] {"id"});
                        ps.setString(1, aReport.getCode());
                        ps.setInt(2, aReport.getYear());
                        return ps;
                    }
                },
                keyHolder);

        return 0L;
    }

    public void updateRecord(Report report) {
        // TODO
    }

    public void deleteReport(long reportId) {
        //TODO
    }

    public Record readReport(long reportId) {
        //TODO
        return null;
    }

    public List<Record> getAllReportsForParentReport(long parentReportId) {
        //TODO
        return null;
    }

    public void deleteAllReportsForParentReport(long parentReportId) {
        // TODO
    }
}

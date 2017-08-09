package com.stefan.buchalter.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReportRequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRequestValidator.class);

    public void validateY(int year) {
        validateYear(year);
    }

    public void validateYQ(int year, int quarter) {
        validateYear(year);
        validateQuarter(quarter);
    }

    public void validateYQM(int year, int quarter, int month) {
        validateYear(year);
        validateQuarter(quarter);
        validateMonth(month);
        validateMonthForQuarter(quarter, month);
    }

    private void validateYear(int year) {
        if (year < 2000 || year > 9999) {
            LOGGER.warn("Validation failed. Year out of range [{}]", year);
            throw new IllegalArgumentException("Invalid year:" + year);
        }
    }

    private void validateQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            LOGGER.warn("Validation failed. Quarter out of range [{}]", quarter);
            throw new IllegalArgumentException("Invalid quarter:" + quarter);
        }
    }

    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            LOGGER.warn("Validation failed. Month out of range [{}]", month);
            throw new IllegalArgumentException("Invalid month:" + month);
        }
    }

    private void validateMonthForQuarter(int quarter, int month) {
        if (month < 3 * (quarter - 1) + 1 || month > 3 * (quarter - 1) + 3) {
            LOGGER.warn("Validation failed. Invalid month for quarter. Q=[{}], M=[{}]", quarter, month);
            throw  new IllegalArgumentException("Invalid month for quarter: Q=" + quarter + " M=" + month);
        }
    }
}

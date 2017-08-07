package com.stefan.buchalter.web.validator;

import org.springframework.stereotype.Component;

@Component
public class ReportRequestValidator {


    public void validateY(int year) {
        validateYear(year);
    }

    public void validateYQ(int year, int quarter) {
        validateYear(year);
        validateQuarter(quarter);
    }

    public void validateYQM(int yera, int quarter, int month) {
        validateYear(yera);
        validateQuarter(quarter);
        validateMonth(month);
        validateMonthForQuarter(quarter, month);
    }

    private void validateYear(int year) {
        if (year < 2000 || year > 9999) {
            throw new IllegalArgumentException("Invalid year:" + year);
        }
    }

    private void validateQuarter(int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Invalid quarter:" + quarter);
        }
    }

    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month:" + month);
        }
    }

    private void validateMonthForQuarter(int quarter, int month) {
        if (month < 3 * (quarter - 1) + 1 || month > 3 * (quarter - 1) + 3) {
            throw  new IllegalArgumentException("Invalid month for quarter: Q=" + quarter + " M=" +month);
        }
    }
}

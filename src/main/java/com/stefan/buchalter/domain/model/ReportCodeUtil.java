package com.stefan.buchalter.domain.model;

public class ReportCodeUtil {

    public static String createYCode(int year) {
        return "R" + year;
    }

    public static String createYQCode(int year, int quarter) {
        return "R" + year + "Q" + quarter;
    }

    public static String createYQMCode(int year, int quarter, int month) {
        return "R" + year + "Q" + quarter + "M" + month;
    }

    public static String extractYCode(String reportCode) {
        return reportCode.substring(0, 5);
    }

    public static String extractYQCode(String reportCode) {
        return reportCode.substring(0, 7);
    }
}

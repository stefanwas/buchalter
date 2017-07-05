package com.stefan.buchalter.domain.record;

import com.stefan.buchalter.domain.report.Report;

import java.time.LocalDate;

public class Record {

    public enum Type {
        VAT, PIT;
    }

    private Long id;
    private String title;
    private LocalDate date;
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}

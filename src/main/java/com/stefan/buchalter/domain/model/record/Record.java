package com.stefan.buchalter.domain.model.record;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stefan.buchalter.web.config.RecordDeserializer;

import java.time.LocalDate;

@JsonDeserialize(using = RecordDeserializer.class)
public class Record {

    public enum Type {
        VAT, PIT;
    }

    public enum VatRate {
        VAT_23(0.23), VAT_8(0.08), VAT_ZW(0.0);

        private double rate;

        VatRate(double rate) {
            this.rate = rate;
        }

        public double getRate() {
            return rate;
        }
    }

    private Long id;
    private String title;
    private LocalDate date;
    private Type type;
    private double netValue;
    private VatRate vatRate;
    private double vatValue;            // no setter
    private double grossValue;          // no setter
    private double pitValue;
    private double vatDeductionRate;
    private double vatDeductionValue;   // no setter

    public Record(String title, LocalDate date, double pitValue) {
        this.type = Type.PIT;
        this.title = title;
        this.date = date;
        this.pitValue = pitValue;
    }

    public Record(String title, LocalDate date, double netValue, VatRate vatRate, double vatDeductionRate) {
        this.type = Type.VAT;
        this.title = title;
        this.date = date;
        this.netValue = netValue;
        this.vatRate = vatRate;
        this.vatDeductionRate = vatDeductionRate;
        recalculate();
    }

    private void recalculate() {
        if (type == Type.VAT) {
            vatValue = vatRate != null && vatRate != VatRate.VAT_ZW ? netValue * vatRate.getRate() : 0.0;
            grossValue = netValue + vatValue;
            vatDeductionValue = vatValue * vatDeductionRate;
            pitValue = grossValue - vatDeductionValue;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public LocalDate getDate() {
        return date;
    }

//    public void setDate(LocalDate date) {
//        this.date = date;
//    }

    public Type getType() {
        return type;
    }

//    public void setType(Type type) {
//        this.type = type;
//    }

    public double getPitValue() {
        return pitValue;
    }

//    public void setPitValue(double pitValue) {
//        this.pitValue = pitValue;
//    }

    public double getNetValue() {
        return netValue;
    }

//    public void setNetValue(double netValue) {
//        this.netValue = netValue;
//        recalculate();
//    }

    public VatRate getVatRate() {
        return vatRate;
    }

//    public void setVatRate(VatRate vatRate) {
//        this.vatRate = vatRate;
//    }

    public double getVatDeductionRate() {
        return vatDeductionRate;
    }

//    public void setVatDeductionRate(double vatDeductionRate) {
//        this.vatDeductionRate = vatDeductionRate;
//    }

    public double getVatValue() {
        return vatValue;
    }

//    public void setVatValue(Double vatValue) {
//        this.vatValue = vatValue;
//    }

    public double getGrossValue() {
        return grossValue;
    }

//    public void setGrossValue(Double grossValue) {
//        this.grossValue = grossValue;
//    }

    public double getVatDeductionValue() {
        return vatDeductionValue;
    }

//    public void setVatDeductionValue(Double vatDeductionValue) {
//        this.vatDeductionValue = vatDeductionValue;
//    }

    @Override
    public String toString() {
        return "Record {ID=" + id + "|" + title + "|" +  date + "|" + type + "|N=" + netValue
                + "|VAT_R=" + vatRate + "|G=" + grossValue + "|PIT=" + pitValue + "}";
    }
}

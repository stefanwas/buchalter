package com.stefan.buchalter.domain.model.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stefan.buchalter.web.config.MoneySerializer;
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

        @JsonValue
        public double getRate() {
            return rate;
        }
    }

    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Type type;
    @JsonSerialize(using = MoneySerializer.class)
    private double netValue;
    private VatRate vatRate;
    @JsonSerialize(using = MoneySerializer.class)
    private double vatValue;            // no setter
    @JsonSerialize(using = MoneySerializer.class)
    private double grossValue;          // no setter
    @JsonSerialize(using = MoneySerializer.class)
    private double pitValue;
    @JsonSerialize(using = MoneySerializer.class)
    private double vatDeductionRate;
    @JsonSerialize(using = MoneySerializer.class)
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

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public double getPitValue() {
        return pitValue;
    }

    public double getNetValue() {
        return netValue;
    }

    public VatRate getVatRate() {
        return vatRate;
    }

    public double getVatDeductionRate() {
        return vatDeductionRate;
    }

    public double getVatValue() {
        return vatValue;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public double getVatDeductionValue() {
        return vatDeductionValue;
    }

    @Override
    public String toString() {
        return "Record {ID=" + id + "|" + title + "|" +  date + "|" + type + "|N=" + netValue
                + "|VAT_R=" + vatRate + "|G=" + grossValue + "|PIT=" + pitValue + "}";
    }
}

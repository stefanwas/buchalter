package com.stefan.buchalter.domain.record;

public class VatRecord extends PitRecord {

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

    private double netValue;
    private VatRate vatRate;
    private double vatValue;
    private double grossValue;
    private double vatDeductionRate;
    private double vatDeductionValue;

    public VatRecord() {
        setType(Type.VAT);
    }

    public VatRecord(double netValue, VatRate vatRate, double vatDeductionRate) {
        this.netValue = netValue;
        this.vatRate = vatRate;
        this.vatDeductionRate = vatDeductionRate;
        recalculate();
    }

    private void recalculate() {
        vatValue = vatRate != null && vatRate != VatRate.VAT_ZW ? netValue * vatRate.getRate() : 0.0;
        grossValue = netValue + vatValue;
        vatDeductionValue = vatValue * vatDeductionRate;
        setPitValue(grossValue - vatDeductionValue);
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public VatRate getVatRate() {
        return vatRate;
    }

    public void setVatRate(VatRate vatRate) {
        this.vatRate = vatRate;
    }

    public double getVatDeductionRate() {
        return vatDeductionRate;
    }

    public void setVatDeductionRate(double vatDeductionRate) {
        this.vatDeductionRate = vatDeductionRate;
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
}

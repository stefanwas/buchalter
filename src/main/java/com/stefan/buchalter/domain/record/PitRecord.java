package com.stefan.buchalter.domain.record;

public class PitRecord extends Record {

    private double pitValue;

    public PitRecord() {
        setType(Type.PIT);
    }

    public double getPitValue() {
        return pitValue;
    }

    public void setPitValue(double pitValue) {
        this.pitValue = pitValue;
    }
}

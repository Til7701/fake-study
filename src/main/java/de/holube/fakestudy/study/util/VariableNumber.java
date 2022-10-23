package de.holube.fakestudy.study.util;

public class VariableNumber extends Number {

    private final double value;

    public VariableNumber(double base, double maxDiff) {
        value = (((Math.random() * 2) - 1) < 0) ? base + Math.random() * maxDiff : base - Math.random() * maxDiff;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

}

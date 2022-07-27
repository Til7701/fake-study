package de.holube.fakestudy.util;

public class VariableNumber extends Number {

    private final double value;

    public VariableNumber(double base, double maxDiff) {
        value = (((Math.random() * 2) - 1) < 0) ? base + Math.random() * maxDiff : base - Math.random() * maxDiff;
    }

    private double getValue() {
        return value;
    }

    @Override
    public int intValue() {
        return (int) getValue();
    }

    @Override
    public long longValue() {
        return (long) getValue();
    }

    @Override
    public float floatValue() {
        return (float) getValue();
    }

    @Override
    public double doubleValue() {
        return getValue();
    }
}

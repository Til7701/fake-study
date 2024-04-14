package de.holube.fakestudy.util;

import java.util.concurrent.ThreadLocalRandom;

public class VariableNumber extends Number {

    private final double value;

    public VariableNumber(double base, double maxDiff) {
        if (ThreadLocalRandom.current().nextDouble() < 0.5)
            value = base + (ThreadLocalRandom.current().nextDouble() * maxDiff);
        else
            value = base - (ThreadLocalRandom.current().nextDouble() * maxDiff);
    }

    public static VariableNumber fromDiff(double base, double diff) {
        return new VariableNumber(base, diff);
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

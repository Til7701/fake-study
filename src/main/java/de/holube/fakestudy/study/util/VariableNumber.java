package de.holube.fakestudy.study.util;

import java.util.concurrent.ThreadLocalRandom;

public class VariableNumber extends Number {

    private final double value;

    public VariableNumber(double base, double maxDiff) {
        if (ThreadLocalRandom.current().nextDouble() < 0.5)
            value = base + (ThreadLocalRandom.current().nextDouble() * maxDiff);
        else
            value = base - (ThreadLocalRandom.current().nextDouble() * maxDiff);
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

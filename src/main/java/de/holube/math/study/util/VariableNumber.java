package de.holube.math.study.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A helper class to create a Number with a random value in a given interval.
 */
public class VariableNumber extends Number {

    private final double value;

    public VariableNumber(double base, double maxDiff) {
        this(base, maxDiff, ThreadLocalRandom.current());
    }

    public VariableNumber(double base, double maxDiff, Random random) {
        if (random.nextBoolean())
            value = base + (random.nextDouble() * maxDiff);
        else
            value = base - (random.nextDouble() * maxDiff);
    }

    public static VariableNumber fromDiff(double base, double diff) {
        return new VariableNumber(base, diff);
    }

    public static VariableNumber fromDiff(double base, double diff, Random random) {
        return new VariableNumber(base, diff, random);
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

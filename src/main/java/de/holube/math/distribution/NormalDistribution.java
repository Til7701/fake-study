package de.holube.math.distribution;

import java.util.Random;

public class NormalDistribution implements Distribution {

    private final double mean;
    private final double sd;
    private final Random random;

    public NormalDistribution(double mean, double sd) {
        this(mean, sd, new Random());
    }

    public NormalDistribution(double mean, double sd, Random random) {
        this.mean = mean;
        this.sd = sd;
        this.random = random;
    }

    @Override
    public double getMean() {
        return mean;
    }

    @Override
    public double getStandardDeviation() {
        return sd;
    }

    @Override
    public double getMin() {
        return Double.MIN_VALUE;
    }

    @Override
    public double getMax() {
        return Double.MAX_VALUE;
    }

    @Override
    public double sample() {
        return random.nextGaussian() * sd + mean;
    }

}

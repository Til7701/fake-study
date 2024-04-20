package de.holube.math.distribution;

import java.util.Random;

/**
 * This class represents a normal distribution.
 */
public class NormalDistribution implements Distribution {

    private final double mean;
    private final double sd;
    private final Random random;

    /**
     * Creates a normal distribution with the given mean and standard deviation.
     *
     * @param mean the mean of the distribution
     * @param sd   the standard deviation
     */
    public NormalDistribution(double mean, double sd) {
        this(mean, sd, new Random());
    }

    /**
     * Creates a normal distribution with the given mean and standard deviation. The random object is used to generate
     * the samples.
     *
     * @param mean   the mean of the distribution
     * @param sd     the standard deviation
     * @param random the random object to use
     */
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
    public double getLowerBound() {
        return Double.MIN_VALUE;
    }

    @Override
    public double getUpperBound() {
        return Double.MAX_VALUE;
    }

    @Override
    public double sample() {
        return random.nextGaussian() * sd + mean;
    }

}

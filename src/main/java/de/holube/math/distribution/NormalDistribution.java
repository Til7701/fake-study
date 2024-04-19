package de.holube.math.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class NormalDistribution implements Distribution {

    private final org.apache.commons.math3.distribution.NormalDistribution distribution;

    public NormalDistribution(double mean, double sd) {
        RandomGenerator randomGenerator = new SynchronizedRandomGenerator(new Well19937c());
        distribution = new org.apache.commons.math3.distribution.NormalDistribution(randomGenerator, mean, sd);
    }

    @Override
    public double getMean() {
        return distribution.getMean();
    }

    @Override
    public double getStandardDeviation() {
        return distribution.getStandardDeviation();
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
        return distribution.sample();
    }

}

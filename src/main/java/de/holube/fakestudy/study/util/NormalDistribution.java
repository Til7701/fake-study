package de.holube.fakestudy.study.util;

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
        return 0;
    }

    @Override
    public double getMax() {
        return 0;
    }

    @Override
    public double sample() {
        return distribution.sample();
    }

}

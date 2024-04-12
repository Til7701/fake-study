package de.holube.fakestudy.study.util;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

final class NormalDistribution {

    private final org.apache.commons.math3.distribution.NormalDistribution distribution;

    public NormalDistribution(double mean, double sd) {
        RandomGenerator randomGenerator = new SynchronizedRandomGenerator(new Well19937c());
        distribution = new org.apache.commons.math3.distribution.NormalDistribution(randomGenerator, mean, sd);
    }

    public double sample() {
        return distribution.sample();
    }

}

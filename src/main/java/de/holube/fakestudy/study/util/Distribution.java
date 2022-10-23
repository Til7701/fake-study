package de.holube.fakestudy.study.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

/**
 * This class represents a Distribution.
 * It is based on a normal distribution, but it makes sure the results are in a given window.
 * This is defined via the min and mix attributes.
 * This class can be made thread-safe by using the SynchronizedRandomGenerator. See constructor.
 */
@Slf4j
public class Distribution {

    private final NormalDistribution normalDistribution;
    @Getter
    private final VariableNumber min;
    @Getter
    private final VariableNumber max;
    @Getter
    private final double type;

    /**
     * Creates a Distribution with the given behaviour.
     *
     * @param min  lower bound for results
     * @param max  upper bound for results
     * @param type defines the mean relative to min and max.
     *             -1 -> first quarter
     *             0 -> in the middle
     *             1 -> third quarter
     * @param sd   the standard deviation
     */
    public Distribution(VariableNumber min, VariableNumber max, double type, VariableNumber sd) {
        this.min = min;
        this.max = max;
        this.type = type;
        double mean = ((max.doubleValue() - min.doubleValue()) / 2.0);
        mean += ((mean - min.doubleValue()) / 2.0) * type;
        RandomGenerator randomGenerator = new SynchronizedRandomGenerator(new Well19937c());
        normalDistribution = new NormalDistribution(randomGenerator, mean, sd.doubleValue());
        //normalDistribution = new NormalDistribution(mean, sd.doubleValue());
    }

    /**
     * This method returns a sample of the Distribution.
     * The sample is guaranteed to be between min and max (both inclusive).
     *
     * @return a sample of the Distribution
     */
    public double sample() {
        double sample;
        int counter = 0;

        do {
            sample = normalDistribution.sample();
            counter++;
            if (counter > 1000) {
                LOG.error("sample counter exceeded 1000");
                return min.doubleValue() + (Math.random() * (max.doubleValue() - min.doubleValue()));
            }
        } while (sample < min.doubleValue() || sample > max.doubleValue());

        return sample;
    }

}

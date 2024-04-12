package de.holube.fakestudy.study.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a Distribution.
 * It is based on a normal distribution, but it makes sure the results are in a given window.
 * This is defined via the min and mix attributes.
 * This class can be made thread-safe by using the SynchronizedRandomGenerator. See constructor.
 */
@Slf4j
public class Distribution {

    private static final int MAX_SAMPLE_TRIES = 1_000_000;

    private final NormalDistribution normalDistribution;
    @Getter
    private final Number min;
    @Getter
    private final Number max;
    @Getter
    private final double type;
    private final Number sd;
    private final double mean;

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
    public Distribution(@NonNull Number min, @NonNull Number max, double type, @NonNull Number sd) {
        this.min = min;
        this.max = max;
        this.type = type;
        this.sd = sd;
        if (min.doubleValue() > max.doubleValue()) throw new IllegalArgumentException("min must be smaller than max");

        double range = max.doubleValue() - min.doubleValue();
        double halfRange = range / 2.0;
        double middle = min.doubleValue() + halfRange;
        if (type == 0) {
            mean = middle;
        } else if (type < 0) {
            double distanceToMax = halfRange / (type - 1);
            mean = min.doubleValue() + distanceToMax;
        } else {
            double distanceToMax = halfRange / (type + 1);
            mean = max.doubleValue() - distanceToMax;
        }

        normalDistribution = new NormalDistribution(mean, sd.doubleValue());
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
            if (counter > MAX_SAMPLE_TRIES) {
                LOG.error("sample counter exceeded {}. Min: {}, Max: {}, SD: {}, Mean: {}", MAX_SAMPLE_TRIES, min.doubleValue(), max.doubleValue(), sd.doubleValue(), mean);
                return min.doubleValue() + (Math.random() * (max.doubleValue() - min.doubleValue()));
            }
        } while (sample < min.doubleValue() || sample > max.doubleValue());

        return sample;
    }

}

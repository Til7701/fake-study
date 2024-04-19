package de.holube.math.distribution;

import de.holube.math.distribution.exception.SampleException;
import lombok.Getter;

/**
 * This class represents a Distribution.
 * It is based on a normal distribution, but it makes sure the results are in a given window.
 * This is defined via the min and mix attributes.
 * This class can be made thread-safe by using the SynchronizedRandomGenerator. See constructor.
 */
@Getter
public class MinMaxDistribution extends NormalDistribution {

    private static final int MAX_SAMPLE_TRIES = 1_000_000;

    private final double min;
    private final double max;
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
     * @throws IllegalArgumentException if min is greater than max
     */
    public MinMaxDistribution(double min, double max, double type, double sd) {
        super(createMeanFromType(min, max, type), sd);
        if (min > max) throw new IllegalArgumentException("min must be smaller than max");
        this.min = min;
        this.max = max;
        this.type = type;
    }

    private static double createMeanFromType(double min, double max, double type) {
        double range = max - min;
        double halfRange = range / 2.0;
        double middle = min + halfRange;
        if (type == 0) {
            return middle;
        } else if (type < 0) {
            double distanceToMax = halfRange / (type - 1);
            return min + distanceToMax;
        } else {
            double distanceToMax = halfRange / (type + 1);
            return max - distanceToMax;
        }
    }

    /**
     * This method returns a sample of the Distribution.
     * The sample is guaranteed to be between min and max (both inclusive).
     *
     * @return a sample of the Distribution
     */
    @Override
    public double sample() {
        try {
            return attemptToGetSample();
        } catch (IllegalStateException e) {
            return min + (Math.random() * (max - min));
        }
    }

    /**
     * This method returns a sample of the Distribution.
     * The sample is guaranteed to be between min and max (both inclusive).
     *
     * @return a sample of the Distribution
     * @throws SampleException if the sample could not be created
     */
    @Override
    public double sampleChecked() throws SampleException {
        try {
            return attemptToGetSample();
        } catch (IllegalStateException e) {
            throw new SampleException("failed to create sample", e);
        }
    }

    private double attemptToGetSample() {
        double sample;
        int counter = 0;

        do {
            sample = super.sample();
            counter++;
            if (counter > MAX_SAMPLE_TRIES)
                throw new IllegalStateException("Too many samples");
        } while (sample < min || sample > max);

        return sample;
    }

}

package de.holube.fakestudy;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Distribution {
    public static final int LINKS = -1;
    public static final int NORMAL = 0;
    public static final int RECHTS = 1;

    private final NormalDistribution normalDistribution;
    private final VariableNumber min;
    private final VariableNumber max;
    private final double type;

    public Distribution(VariableNumber min, VariableNumber max, double type, VariableNumber sd) {
        this.min = min;
        this.max = max;
        this.type = type;
        double mean = ((max.doubleValue() - min.doubleValue()) / 2.0);
        mean += ((mean - min.doubleValue()) / 2.0) * type;
        normalDistribution = new NormalDistribution(mean, sd.doubleValue());
    }

    public double sample() {
        double sample = 0;

        do {
            sample = normalDistribution.sample();
        } while (sample < min.doubleValue() || sample > max.doubleValue());

        return sample;
    }

    public VariableNumber getMin() {
        return min;
    }

    public VariableNumber getMax() {
        return max;
    }

    public double getType() {
        return type;
    }
}

package de.holube.fakestudy.study.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.distribution.NormalDistribution;

@Slf4j
public class Distribution {

    private final NormalDistribution normalDistribution;
    @Getter
    private final VariableNumber min;
    @Getter
    private final VariableNumber max;
    @Getter
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
        double sample;
        int counter = 0;

        do {
            sample = normalDistribution.sample();
            counter++;
            if (counter > 1000) {
                LOG.error("sample counter exceeded 1000");
                return Math.random() * (min.doubleValue() + (max.doubleValue() - min.doubleValue()));
            }
        } while (sample < min.doubleValue() || sample > max.doubleValue());

        return sample;
    }

}

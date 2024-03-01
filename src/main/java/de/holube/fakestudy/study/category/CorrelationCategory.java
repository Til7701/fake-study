package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.correlate.Correlator;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CorrelationCategory extends NumCategory {

    private final NumberCategory origin;
    private double min;
    private double max;
    private Distribution distribution;
    private Correlator correlator;

    public CorrelationCategory(@NonNull String name, @NonNull NumberCategory origin) {
        super(name);
        this.origin = origin;
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new Double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            double value = correlator.correlate(
                    origin.getDistribution().getMin().doubleValue(),
                    origin.getDistribution().getMax().doubleValue(),
                    origin.getResults()[i],
                    min,
                    max
            );
            final double v = value;
            int counter = 0;
            do {
                if (counter == 1000) {
                    LOG.error("Counter exceeded 1000 tries");
                }
                value = v + distribution.sample();
                counter++;
            } while (value < min || value > max);

            results[i] = value;
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    public CorrelationCategory setMin(double min) {
        this.min = min;
        return this;
    }

    public CorrelationCategory setMax(double max) {
        this.max = max;
        return this;
    }

    public CorrelationCategory setDistribution(@NonNull Distribution distribution) {
        this.distribution = distribution;
        return this;
    }

    public CorrelationCategory setCorrelator(@NonNull Correlator correlator) {
        this.correlator = correlator;
        return this;
    }
}

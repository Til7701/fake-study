package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.correlate.Correlator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class CorrelationCategory extends NumCategory {

    private final NumberCategory origin;
    private double min;
    private double max;
    private Distribution distribution;
    private Correlator correlator;

    public CorrelationCategory(NumberCategory origin) {
        this.origin = origin;
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            double value = correlator.correlate(
                    origin.getDistribution().getMin().doubleValue(),
                    origin.getDistribution().getMax().doubleValue(),
                    origin.getDoubleResults()[i],
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

}

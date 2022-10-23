package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.correlate.Correlator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
            value += distribution.sample();

            results[i] = value;
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

}

package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.util.Distribution;
import lombok.Getter;

@Getter
public class NumberCategory extends NumCategory {

    protected Distribution distribution;

    public NumberCategory(String name) {
        super(name);
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new Double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            results[i] = distribution.sample();
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    @Override
    public double getMin() {
        return distribution.getMin().doubleValue();
    }

    @Override
    public double getMax() {
        return distribution.getMax().doubleValue();
    }

    public NumberCategory setDistribution(Distribution distribution) {
        this.distribution = distribution;
        return this;
    }

}

package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.util.Distribution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberCategory extends NumCategory {

    private Distribution distribution;

    @Override
    public void calculate(int amountSubjects) {
        results = new double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            results[i] = distribution.sample();
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    public double getMin() {
        return distribution.getMin().doubleValue();
    }

    public double getMax() {
        return distribution.getMax().doubleValue();
    }

}

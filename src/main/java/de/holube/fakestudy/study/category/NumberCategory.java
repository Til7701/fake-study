package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.Distribution;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NumberCategory extends NumCategory {

    protected Distribution distribution;

    public NumberCategory(@NonNull String name) {
        super(name);
    }

    public static Builder builder(@NonNull String key, @NonNull String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new Double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            results[i] = distribution.sample();
            double tmp = results[i];
            if (results[i] == null) {
                LOG.error("result entry is null: {} tmp: {}", results[i], tmp);
            }
            results[i] = Math.round(tmp * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
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

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        private String name;
        private Double missingValue;
        private double missingPercentage;
        private int decimalPlaces;
        private Distribution distribution;

        public NumberCategory build() {
            NumberCategory category = new NumberCategory(name);
            category.setMissingValue(missingValue);
            category.setMissingPercentage(missingPercentage);
            category.setDecimalPlaces(decimalPlaces);
            category.distribution = distribution;

            study.add(key, category);
            return category;
        }
    }

}

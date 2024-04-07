package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.correlate.Correlator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
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

    public static Builder builder(@NonNull String key, @NonNull String name) {
        return new Builder().key(key).name(name);
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

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        private String name;
        private Double missingValue;
        private double missingPercentage;
        private String originKey;
        private double min;
        private double max;
        private Distribution distribution;
        private Correlator correlator;
        private int decimalPlaces;

        public CorrelationCategory build() {
            Category<?> potentialOrigin = study.getCategories().get(originKey);
            if (potentialOrigin == null)
                throw new IllegalStateException("Origin category not found: " + originKey);
            if (!(potentialOrigin instanceof NumberCategory origin))
                throw new IllegalStateException("Origin category is not a NumberCategory: " + originKey);

            CorrelationCategory category = new CorrelationCategory(name, origin);
            category.setMissingValue(missingValue);
            category.setMissingPercentage(missingPercentage);
            category.min = min;
            category.max = max;
            category.distribution = distribution;
            category.correlator = correlator;

            study.add(key, category);
            return category;
        }
    }

}

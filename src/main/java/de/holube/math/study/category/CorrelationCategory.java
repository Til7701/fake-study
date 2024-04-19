package de.holube.math.study.category;

import de.holube.math.correlate.Correlator;
import de.holube.math.distribution.Distribution;
import de.holube.math.study.Study;
import de.holube.math.study.exception.CalculationException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.RoundingMode;

/**
 * This class represents a Category, where the subjects answer a question with a value from a distribution, which is
 * correlated to another category.
 */
@Getter
public class CorrelationCategory extends NumberCategory {

    private final NumberCategory origin;
    private double min;
    private double max;
    private Distribution distribution;
    private Correlator correlator;

    public CorrelationCategory(NumberCategory origin) {
        super();
        this.origin = origin;
    }

    public CorrelationCategory(String name, @NonNull NumberCategory origin) {
        super(name);
        this.origin = origin;
    }

    public static Builder builder(String key, String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) throws CalculationException {
        results = new Double[amountSubjects];
        final double originMin = origin.getMin();
        final double originMax = origin.getMax();

        for (int i = 0; i < results.length; i++) {
            final double correlatedValue = correlator.correlate(
                    originMin, originMax,
                    origin.getResults()[i],
                    min, max
            );
            double result;
            int counter = 0;
            do {
                result = correlatedValue + distribution.sample();
                counter++;
                if (counter == 1000)
                    throw new CalculationException("Correlation criteria cannot be met!");
            } while (result < min || result > max);

            results[i] = Math.round(result * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        // AbstractCategory
        private String name = DEFAULT_NAME;
        private double missingPercentage = DEFAULT_MISSING_PERCENTAGE;
        // NumberCategory
        private Double missingValue = DEFAULT_MISSING_VALUE;
        private int decimalPlaces = DEFAULT_DECIMAL_PLACES;
        private RoundingMode roundingMode = DEFAULT_ROUNDING_MODE;
        // CorrelationCategory
        private String originKey;
        private NumberCategory origin;
        private double min;
        private double max;
        private Distribution distribution;
        private Correlator correlator;

        public CorrelationCategory build() {
            if (originKey != null) {
                origin = (NumberCategory) study.getCategories().get(originKey);
                if (origin == null)
                    throw new IllegalStateException("Origin category not found: " + originKey);
            }

            CorrelationCategory category = new CorrelationCategory(origin);
            // AbstractCategory
            category.setName(name);
            category.setMissingPercentage(missingPercentage);
            // NumberCategory
            category.setMissingValue(missingValue);
            category.setDecimalPlaces(decimalPlaces);
            category.setRoundingMode(roundingMode);
            // CorrelationCategory
            category.min = min;
            category.max = max;
            category.distribution = distribution;
            category.correlator = correlator;

            if (study != null)
                study.add(key, category);
            return category;
        }
    }

}

package de.holube.math.study.category;

import de.holube.math.distribution.Distribution;
import de.holube.math.distribution.NormalDistribution;
import de.holube.math.study.Study;
import de.holube.math.study.exception.CalculationException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.RoundingMode;

/**
 * This class represents a Category, where the subjects answer a question with a value from a distribution.
 */
@Getter
public class DistributionCategory extends NumberCategory {

    public static final Distribution DEFAULT_DISTRIBUTION = new NormalDistribution(0, 1);

    protected Distribution distribution = DEFAULT_DISTRIBUTION;

    public DistributionCategory() {
        super();
    }

    public DistributionCategory(String name) {
        super(name);
    }

    public static Builder builder(String key, String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) throws CalculationException {
        results = new Double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            results[i] = distribution.sample();
            if (results[i] == null) throw new CalculationException("Distribution sample is null!");
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    @Override
    public double getMin() {
        return distribution.getLowerBound();
    }

    @Override
    public double getMax() {
        return distribution.getUpperBound();
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
        // DistributionCategory
        private Distribution distribution = DEFAULT_DISTRIBUTION;

        public DistributionCategory build() {
            DistributionCategory category = new DistributionCategory();
            // AbstractCategory
            category.setName(name);
            category.setMissingPercentage(missingPercentage);
            // NumberCategory
            category.setMissingValue(missingValue);
            category.setDecimalPlaces(decimalPlaces);
            category.setRoundingMode(roundingMode);
            // DistributionCategory
            category.distribution = distribution;

            if (study != null)
                study.add(key, category);
            return category;
        }
    }

}

package de.holube.fakestudy.study.util.correlate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Provides normal correlation of values.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class NormalCorrelator implements Correlator {

    /**
     * This method correlates the given value in the first space to a value in the second space and returns that value.
     * The returned value is a perfect correlation.
     *
     * @param firstMin  lower bound of first space
     * @param firstMax  upper bound of first space
     * @param value     value in the first space
     * @param secondMin lower bound of second space
     * @param secondMax upper bound of second space
     * @return correlated value in second space
     */
    @Override
    public double correlate(double firstMin, double firstMax, double value, double secondMin, double secondMax) {
        value -= firstMin;
        double firstPercent = value / (firstMax - firstMin);

        value = firstPercent * (secondMax - secondMin);
        return value + secondMin;
    }

}

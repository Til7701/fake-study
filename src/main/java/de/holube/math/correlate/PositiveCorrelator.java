package de.holube.math.correlate;

/**
 * Provides correlation of values.
 */
public class PositiveCorrelator implements Correlator {

    PositiveCorrelator() {
    }

    /**
     * This method correlates the given value in the first space to a value in the second space and returns that value.
     * The returned value is a perfect correlation.
     * If {@code sourceMin == sourceMax}, the method returns {@code Double.NaN}.
     *
     * @param sourceMin lower bound of first space
     * @param sourceMax upper bound of first space
     * @param value     value in the first space
     * @param targetMin lower bound of second space
     * @param targetMax upper bound of second space
     * @return correlated value in second space
     */
    @Override
    public double correlate(double sourceMin, double sourceMax, double value, double targetMin, double targetMax) {
        value -= sourceMin;
        double firstPercent = value / (sourceMax - sourceMin);

        value = firstPercent * (targetMax - targetMin);
        return value + targetMin;
    }

}

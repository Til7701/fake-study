package de.holube.math.correlate;

/**
 * This interface is the base for all Correlators.
 * Instances can be obtained via the {@link Correlators} class.
 */
@FunctionalInterface
public interface Correlator {

    /**
     * Behaviour should be defined by implementations.
     *
     * @see PositiveCorrelator#correlate
     * @see NegativeCorrelator#correlate
     */
    double correlate(double sourceMin, double sourceMax, double value, double targetMin, double targetMax);

}
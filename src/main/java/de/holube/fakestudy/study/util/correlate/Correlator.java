package de.holube.fakestudy.study.util.correlate;

/**
 * This interface is the base for all Correlators.
 * Instances can be obtained via the {@link Correlators} class.
 */
public sealed interface Correlator permits NormalCorrelator, InvertedCorrelator {

    /**
     * Behaviour should be defined by implementations.
     *
     * @see NormalCorrelator
     * @see InvertedCorrelator
     */
    double correlate(double firstMin, double firstMax, double value, double secondMin, double secondMax);

}

package de.holube.math.correlate;

/**
 * This class provides instances for positive and negative correlators.
 */
public final class Correlators {

    public static final PositiveCorrelator POSITIVE_CORRELATOR = new PositiveCorrelator();
    public static final NegativeCorrelator NEGATIVE_CORRELATOR = new NegativeCorrelator();

    private Correlators() {
    }

}

package de.holube.math.correlate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class provides instances for normal and inverted correlators.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Correlators {

    public static final Correlator NORMAL_CORRELATOR = new NormalCorrelator();
    public static final Correlator INVERTED_CORRELATOR = new InvertedCorrelator();

}

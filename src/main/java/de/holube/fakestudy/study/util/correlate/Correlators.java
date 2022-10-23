package de.holube.fakestudy.study.util.correlate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class provides instances for normal and inverted correlators.
 */
@SuppressWarnings("SpellCheckingInspection")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Correlators {

    public static final Correlator NORMAL_CORRELATOR = new NormalCorrelator();
    public static final Correlator INVERTED_CORRELATOR = new InvertedCorrelator();

}

package de.holube.math.study.util;

import de.holube.math.correlate.Correlators;
import de.holube.math.correlate.InvertedCorrelator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvertedCorrelatorTest {

    InvertedCorrelator correlator = (InvertedCorrelator) Correlators.INVERTED_CORRELATOR;

    @Test
    void correlateNonInvertedTest() {
        Assertions.assertDoesNotThrow(() -> correlator.correlate(0, 0, 0, 0, 0));
        assertEquals(Double.NaN, correlator.correlate(0, 0, 0, 0, 0));
    }

    @Test
    void correlateGreaterPositiveTest() {
        assertEquals(8, correlator.correlate(0, 4, 0, 0, 8));
        assertEquals(6, correlator.correlate(0, 4, 1, 0, 8));
        assertEquals(2, correlator.correlate(0, 4, 3, 0, 8));
        assertEquals(0, correlator.correlate(0, 4, 4, 0, 8));
    }

    @Test
    void correlateGreaterNegativeTest() {
        assertEquals(-8, correlator.correlate(-4, 0, 0, -8, 0));
        assertEquals(-6, correlator.correlate(-4, 0, -1, -8, 0));
        assertEquals(-2, correlator.correlate(-4, 0, -3, -8, 0));
        assertEquals(0, correlator.correlate(-4, 0, -4, -8, 0));
    }

    @Test
    void correlateLesserPositiveTest() {
        assertEquals(4, correlator.correlate(0, 8, 0, 0, 4));
        assertEquals(3.5, correlator.correlate(0, 8, 1, 0, 4));
        assertEquals(0.5, correlator.correlate(0, 8, 7, 0, 4));
        assertEquals(0, correlator.correlate(0, 8, 8, 0, 4));
    }

    @Test
    void correlateLesserNegativeTest() {
        assertEquals(-4, correlator.correlate(-8, 0, 0, -4, 0));
        assertEquals(-3.5, correlator.correlate(-8, 0, -1, -4, 0));
        assertEquals(-2.5, correlator.correlate(-8, 0, -3, -4, 0));
        assertEquals(0, correlator.correlate(-8, 0, -8, -4, 0));
    }

}

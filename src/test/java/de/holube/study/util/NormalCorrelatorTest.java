package de.holube.study.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalCorrelatorTest {

    NormalCorrelator correlator = (NormalCorrelator) Correlators.NORMAL_CORRELATOR;

    @Test
    void correlateNonNormalTest() {
        assertDoesNotThrow(() -> correlator.correlate(0, 0, 0, 0, 0));
        assertEquals(Double.NaN, correlator.correlate(0, 0, 0, 0, 0));

    }

    @Test
    void correlateGreaterPositiveTest() {
        assertEquals(0, correlator.correlate(0, 4, 0, 0, 8));
        assertEquals(2, correlator.correlate(0, 4, 1, 0, 8));
        assertEquals(6, correlator.correlate(0, 4, 3, 0, 8));
        assertEquals(8, correlator.correlate(0, 4, 4, 0, 8));
    }

    @Test
    void correlateGreaterNegativeTest() {
        assertEquals(0, correlator.correlate(-4, 0, 0, -8, 0));
        assertEquals(-2, correlator.correlate(-4, 0, -1, -8, 0));
        assertEquals(-6, correlator.correlate(-4, 0, -3, -8, 0));
        assertEquals(-8, correlator.correlate(-4, 0, -4, -8, 0));
    }

    @Test
    void correlateLesserPositiveTest() {
        assertEquals(0, correlator.correlate(0, 8, 0, 0, 4));
        assertEquals(0.5, correlator.correlate(0, 8, 1, 0, 4));
        assertEquals(3.5, correlator.correlate(0, 8, 7, 0, 4));
        assertEquals(4, correlator.correlate(0, 8, 8, 0, 4));
    }

    @Test
    void correlateLesserNegativeTest() {
        assertEquals(0, correlator.correlate(-8, 0, 0, -4, 0));
        assertEquals(-0.5, correlator.correlate(-8, 0, -1, -4, 0));
        assertEquals(-1.5, correlator.correlate(-8, 0, -3, -4, 0));
        assertEquals(-4, correlator.correlate(-8, 0, -8, -4, 0));
    }

}

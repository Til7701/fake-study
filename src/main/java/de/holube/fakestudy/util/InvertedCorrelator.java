package de.holube.fakestudy.util;

public class InvertedCorrelator implements Correlator {

    @Override
    public double correlate(double firstMin, double firstMax, double value, double secondMin, double secondMax) {
        value -= firstMin;
        double firstPercent = value / (firstMax - firstMin);

        firstPercent = 1 - firstPercent;

        value = firstPercent * (secondMax - secondMin);
        return value + secondMin;
    }
}

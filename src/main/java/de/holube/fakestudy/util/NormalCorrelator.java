package de.holube.fakestudy.util;

public class NormalCorrelator implements Correlator {

    @Override
    public double correlate(double firstMin, double firstMax, double value, double secondMin, double secondMax) {
        value -= firstMin;
        double firstPercent = value / (firstMax - firstMin);

        value = firstPercent * (secondMax - secondMin);
        return value + secondMin;
    }
}

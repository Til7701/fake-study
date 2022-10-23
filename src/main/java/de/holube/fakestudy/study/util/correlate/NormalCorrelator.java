package de.holube.fakestudy.study.util.correlate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class NormalCorrelator implements Correlator {

    @Override
    public double correlate(double firstMin, double firstMax, double value, double secondMin, double secondMax) {
        value -= firstMin;
        double firstPercent = value / (firstMax - firstMin);

        value = firstPercent * (secondMax - secondMin);
        return value + secondMin;
    }

}

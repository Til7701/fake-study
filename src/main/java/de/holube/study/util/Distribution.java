package de.holube.study.util;

public interface Distribution {

    double getMean();

    double getStandardDeviation();

    double getMin();

    double getMax();

    double sample();

}

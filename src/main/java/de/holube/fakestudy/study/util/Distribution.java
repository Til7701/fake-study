package de.holube.fakestudy.study.util;

public interface Distribution {

    double getMean();

    double getStandardDeviation();

    double getMin();

    double getMax();

    double sample();

}

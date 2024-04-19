package de.holube.study.util;

import de.holube.study.exception.SampleException;

public interface Distribution {

    double getMean();

    double getStandardDeviation();

    double getMin();

    double getMax();

    double sample();

    default double sampleChecked() throws SampleException {
        return sample();
    }

}

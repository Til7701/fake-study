package de.holube.math.distribution;

import de.holube.math.exception.SampleException;

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

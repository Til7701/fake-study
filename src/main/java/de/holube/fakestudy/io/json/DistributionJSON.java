package de.holube.fakestudy.io.json;

import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("unused")
@Getter
@ToString
public class DistributionJSON {

    private VariableNumberJOSN min;
    private VariableNumberJOSN max;
    private DistributionTypeJSON type;
    private VariableNumberJOSN standardDeviation;

}

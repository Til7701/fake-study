package de.holube.fakestudy.io.json;

import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("unused")
@Getter
@ToString
public class DistributionTypeJSON {

    private DistributionTypeDescriptorJSON descriptor;
    private String other;
    private double value;

}

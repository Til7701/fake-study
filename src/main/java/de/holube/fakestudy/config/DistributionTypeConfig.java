package de.holube.fakestudy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistributionTypeConfig {

    private DistributionTypeDescriptor descriptor;
    private String other;
    private double value;

}

package de.holube.fakestudy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistributionConfig {

    private VarNumConfig min;
    private VarNumConfig max;
    private DistributionTypeConfig type;
    private VarNumConfig standardDeviation;

}

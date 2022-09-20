package de.holube.fakestudy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConstantsConfig {

    private int amountOfSubjects;
    private double missingPercentageBase;
    private double missingPercentageDiff;

}

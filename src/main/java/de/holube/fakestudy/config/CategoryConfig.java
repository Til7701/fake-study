package de.holube.fakestudy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryConfig {

    private CategoriesTypeConfig type;
    private String name;

    // missing
    private String missingValue;
    private VarNumConfig missingPercentage;

    // used by more than one
    private int min;
    private int max;

    // SELECTION
    private String[] options;

    // NUMBER & CORRELATION
    private DistributionConfig distribution;
    private int decimalPlaces;

    // CORRELATION
    private CorrelationType correlate;
    private String origin;

}

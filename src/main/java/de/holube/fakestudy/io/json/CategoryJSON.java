package de.holube.fakestudy.io.json;

import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("unused")
@Getter
@ToString
public class CategoryJSON {

    private CategoriesTypeJSON type;
    private String name;

    // missing
    private String missingValue;
    private VariableNumberJOSN missingPercentage;

    // used by more than one
    private int min;
    private int max;

    // SELECTION
    private String[] options;

    // NUMBER & CORRELATION
    private DistributionJSON distribution;
    private int decimalPlaces;

    // CORRELATION
    private CorrelationTypeJSON correlate;
    private String origin;

}

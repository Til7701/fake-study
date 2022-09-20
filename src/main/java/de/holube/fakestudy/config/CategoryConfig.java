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

    // SELECTION
    private String[] options;
    private String min;
    private String max;

}

package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the base class for all categories.
 * A category represents one data point. E.g. one question the subjects were asked.
 */
@Getter
@Setter
public abstract class Category {

    protected double missingPercentage;
    private String name;

    public abstract void calculate(int amountSubjects);

    public abstract void setMissing();

    public abstract String[] getStringResults();

}

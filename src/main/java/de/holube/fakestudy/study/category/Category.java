package de.holube.fakestudy.study.category;

import lombok.Getter;

/**
 * This is the base class for all categories.
 * A category represents one data point. E.g. one question the subjects were asked.
 */
@Getter
public abstract class Category<T> {

    protected double missingPercentage;
    private String name;

    public abstract void calculate(int amountSubjects);

    public abstract void setMissing();

    public abstract T[] getResults();

    public abstract String[] getStringResults();

    public Category<T> setName(String name) {
        this.name = name;
        return this;
    }

    public Category<T> setMissingPercentage(double missingPercentage) {
        this.missingPercentage = missingPercentage;
        return this;
    }

}

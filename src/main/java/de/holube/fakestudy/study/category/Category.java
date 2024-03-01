package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This is the base class for all categories.
 * A category represents one data point. E.g. one question the subjects were asked.
 */
@Getter
@RequiredArgsConstructor
public abstract class Category<T> {

    private final String name;

    protected double missingPercentage;
    protected T missingValue;
    protected T[] results;

    public abstract void calculate(int amountSubjects);

    public abstract String[] getStringResults();

    public void setMissing() {
        for (int i = 0; i < results.length; i++) {
            if (Math.random() < missingPercentage)
                results[i] = missingValue;
        }
    }

    public Category<T> setMissingPercentage(double missingPercentage) {
        this.missingPercentage = missingPercentage;
        return this;
    }

    public Category<T> setMissingValue(T missingValue) {
        this.missingValue = missingValue;
        return this;
    }

}

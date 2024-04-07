package de.holube.fakestudy.study.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This is the base class for all categories.
 * A category represents one data point. E.g. one question the subjects were asked.
 */
@Getter
@RequiredArgsConstructor
public abstract class Category<T> {

    private final String name;

    @Setter(AccessLevel.PROTECTED)
    protected double missingPercentage;
    @Setter(AccessLevel.PROTECTED)
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

}

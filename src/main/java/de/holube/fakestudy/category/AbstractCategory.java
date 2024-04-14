package de.holube.fakestudy.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This is the base class for all categories.
 * A category represents one data point. E.g. one question the subjects were asked.
 */
@Getter
@Setter
public abstract class AbstractCategory<T> implements Category<T> {

    public static final String DEFAULT_NAME = "Unnamed";
    public static final double DEFAULT_MISSING_PERCENTAGE = 0.1;
    public static final Object DEFAULT_MISSING_VALUE = null;

    protected String name = DEFAULT_NAME;
    protected double missingPercentage = DEFAULT_MISSING_PERCENTAGE;
    protected T missingValue = null;

    @Setter(AccessLevel.NONE)
    protected T[] results;

    protected AbstractCategory() {

    }

    protected AbstractCategory(String name) {
        this.name = name;
    }

    @Override
    public void setMissing() {
        for (int i = 0; i < results.length; i++) {
            if (Math.random() < missingPercentage)
                results[i] = missingValue;
        }
    }

    @Override
    public String[] getStringResults() {
        String[] strings = new String[results.length];
        for (int i = 0; i < results.length; i++) {
            strings[i] = results[i].toString();
        }
        return strings;
    }

}

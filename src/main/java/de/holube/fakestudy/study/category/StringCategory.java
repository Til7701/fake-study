package de.holube.fakestudy.study.category;

/**
 * This is the base class for all categories, which have a string as result.
 */
public abstract class StringCategory extends AbstractCategory<String> {

    public static final String DEFAULT_MISSING_VALUE = "missing";

    protected StringCategory() {
        super();
        missingValue = DEFAULT_MISSING_VALUE;
    }

    protected StringCategory(String name) {
        super(name);
        missingValue = DEFAULT_MISSING_VALUE;
    }

    @Override
    public String[] getStringResults() {
        return results;
    }

}

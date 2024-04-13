package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.CalculationException;

/**
 * This is the base class for all categories.
 *
 * @param <R> The type of the result of the category.
 */
public interface Category<R> {

    /**
     * This method calculates the results of the category.
     *
     * @param amountSubjects the amount of subjects in the study
     * @throws CalculationException if the calculation fails
     */
    void calculate(int amountSubjects) throws CalculationException;

    /**
     * This method returns the results of the category as an array of strings. This is useful for exporting the results.
     * The format of the strings is not specified.
     *
     * @return the results as an array of strings
     */
    String[] getStringResults();

    /**
     * This method returns the name of the category.
     *
     * @return the name of the category
     */
    String getName();

    /**
     * This method returns the results of the category.
     *
     * @return the results of the category
     */
    R[] getResults();

    /**
     * This method returns the percentage of missing values in the category. The percentage is a value between 0 and 1.
     * A value of 0 means that there are no missing values, a value of 1 means that all values are missing. The value
     * is not calculated, but stored in the category.
     *
     * @return the percentage of missing values
     */
    double getMissingPercentage();

    /**
     * This method returns the value that is used to represent missing values in the category.
     *
     * @return the value that is used to represent missing values
     */
    R getMissingValue();

    /**
     * This method sets the missing values in the category.
     */
    void setMissing();

}

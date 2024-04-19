package de.holube.math.study.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * This is the base class for all categories, which have a number as result.
 */
@Setter(AccessLevel.PROTECTED)
@Getter
public abstract class NumberCategory extends AbstractCategory<Double> {

    public static final Double DEFAULT_MISSING_VALUE = -1d;
    public static final int DEFAULT_DECIMAL_PLACES = 2;
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    protected int decimalPlaces = DEFAULT_DECIMAL_PLACES;
    protected RoundingMode roundingMode = DEFAULT_ROUNDING_MODE;

    protected NumberCategory() {
        super();
        missingValue = DEFAULT_MISSING_VALUE;
    }

    protected NumberCategory(String name) {
        super(name);
        missingValue = DEFAULT_MISSING_VALUE;
    }

    private DecimalFormat decimalFormat(int decimalPlaces) {
        StringBuilder decimalString = new StringBuilder("#");
        if (decimalPlaces > 0) {
            decimalString.append(".");
            decimalString.append("#".repeat(decimalPlaces));
        }
        DecimalFormat df = new DecimalFormat(decimalString.toString());
        df.setRoundingMode(roundingMode);

        return df;
    }

    @Override
    public String[] getStringResults() {
        String[] ret = new String[results.length];
        DecimalFormat df = decimalFormat(decimalPlaces);

        for (int i = 0; i < ret.length; i++) {
            ret[i] = df.format(results[i]);
        }

        return ret;
    }

    public double getMin() {
        if (results == null)
            return Double.MIN_VALUE;

        double min = Double.MAX_VALUE;
        for (Double result : results) {
            if (result < min) {
                min = result;
            }
        }
        return min;
    }

    public double getMax() {
        if (results == null)
            return Double.MAX_VALUE;

        double max = Double.MIN_VALUE;
        for (Double result : results) {
            if (result > max) {
                max = result;
            }
        }
        return max;
    }

}

package de.holube.fakestudy;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class NumCategory extends Category {

    protected int decimalPlaces;

    protected double[] results;

    protected double missingValue;

    public double[] getDoubleResults() {
        return results;
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
        String[] ret = new String[results.length];
        DecimalFormat df = decimalFormat(decimalPlaces);

        for (int i = 0; i < ret.length; i++) {
            ret[i] = df.format(results[i]);
        }

        return ret;
    }

    protected DecimalFormat decimalFormat(int decimalPlaces) {
        String decimalString = "#";
        if (decimalPlaces > 0) {
            decimalString = decimalString.concat("." + "#".repeat(decimalPlaces));
        }
        DecimalFormat df = new DecimalFormat(decimalString);
        df.setRoundingMode(RoundingMode.HALF_UP);

        return df;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public double getMissingValue() {
        return missingValue;
    }

    public void setMissingValue(double missingValue) {
        this.missingValue = missingValue;
    }
}

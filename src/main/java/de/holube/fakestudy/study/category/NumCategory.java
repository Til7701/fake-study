package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Getter
public abstract class NumCategory extends Category<Double> {

    @Setter
    protected int decimalPlaces;

    protected Double[] results;

    @Setter
    protected double missingValue;


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
        StringBuilder decimalString = new StringBuilder("#");
        if (decimalPlaces > 0) {
            decimalString.append(".");
            decimalString.append("#".repeat(decimalPlaces));
        }
        DecimalFormat df = new DecimalFormat(decimalString.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);

        return df;
    }

    public abstract double getMin();

    public abstract double getMax();

}

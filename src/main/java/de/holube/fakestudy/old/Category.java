package de.holube.fakestudy.old;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Category {
    String name;
    Distribution distribution;
    public String[] values = new String[Study.AMOUNT_SUBJECTS];

    public Category(String name) {
        this.name = name;

        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(i + 1);
        }

        System.out.println("Created Category: " + name);
    }

    public Category(String name, String[] possibleValues, int min, int max) {
        this.name = name;

        boolean done = false;
        while (!done) {
            int[] counter = new int[possibleValues.length];

            for (int i = 0; i < Study.AMOUNT_SUBJECTS; i++) {
                int valueIndex = (int) (Math.random() * possibleValues.length);
                values[i] = possibleValues[valueIndex];
                counter[valueIndex]++;
            }

            done = true;
            for (int i = 0; i < counter.length; i++) {
                if (counter[i] < min) {
                    done = false;
                    break;
                } else if (counter[i] > max) {
                    done = false;
                    break;
                }
            }
        }

        System.out.println("Created Category: " + name);
    }

    public Category(String name, Distribution distribution, int decimalPlaces) {
        this.name = name;
        this.distribution = distribution;

        DecimalFormat df = decimalFormat(decimalPlaces);

        for (int i = 0; i < values.length; i++) {
            double value = 0;
            value = distribution.get();

            values[i] = df.format(value);
        }

        System.out.println("Created Category: " + name);
    }

    public Category(String name, Category category, int[] range, double varianz, int decimalPlaces) {
        this.name = name;
        this.distribution = category.distribution;

        DecimalFormat df = decimalFormat(decimalPlaces);

        for (int i = 0; i < values.length; i++) {
            double value = correlate(category.distribution.getRange(), Double.valueOf(category.values[i]), range);
            value = MyMath.addVarianz(value, varianz);

            values[i] = df.format(value);
        }

        System.out.println("Created Category: " + name + " correlated with " + category.name);
    }

    private double correlate(int[] firstRange, double value, int[] secondRange) {
        value -= firstRange[0];
        double firstPercent = value / (firstRange[1] - firstRange[0]);

        value = firstPercent * (secondRange[1] - secondRange[0]);
        return value + secondRange[0];
    }

    private DecimalFormat decimalFormat(int decimalPlaces) {
        String decimalString = "#";
        if (decimalPlaces > 0) {
            decimalString.concat("." + "#".repeat(decimalPlaces));
        }
        DecimalFormat df = new DecimalFormat(decimalString);
        df.setRoundingMode(RoundingMode.CEILING);

        return df;
    }

}

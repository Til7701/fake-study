package de.holube.fakestudy;

public class NumberCategory extends NumCategory {

    private Distribution distribution;

    @Override
    public void calculate(int amountSubjects) {

        results = new double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            results[i] = distribution.sample();
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }

    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

}

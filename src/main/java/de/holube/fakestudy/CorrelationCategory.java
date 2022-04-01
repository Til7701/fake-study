package de.holube.fakestudy;

public class CorrelationCategory extends NumCategory {

    private final NumberCategory origin;
    private double min;
    private double max;
    private Distribution distribution;
    private Correlator correlator;

    public CorrelationCategory(NumberCategory origin) {
        this.origin = origin;
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new double[amountSubjects];

        for (int i = 0; i < results.length; i++) {
            double value = correlator.correlate(
                    origin.getDistribution().getMin().doubleValue(),
                    origin.getDistribution().getMax().doubleValue(),
                    origin.getDoubleResults()[i],
                    min,
                    max
            );
            value += distribution.sample();

            results[i] = value;
            results[i] = Math.round(results[i] * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        }
    }

    public NumCategory getOrigin() {
        return origin;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Correlator getCorrelator() {
        return correlator;
    }

    public void setCorrelator(Correlator correlator) {
        this.correlator = correlator;
    }
}

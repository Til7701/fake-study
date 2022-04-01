package de.holube.fakestudy;

public class DiscreteNumberCategory extends NumberCategory {

    @Override
    public void calculate(int amountSubjects) {
        super.calculate(amountSubjects);

        for (int i = 0; i < results.length; i++) {
            results[i] = Math.round(results[i]);
        }
    }
}

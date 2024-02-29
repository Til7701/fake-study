package de.holube.fakestudy.study.category;

public class DiscreteNumberCategory extends NumberCategory {

    @Override
    public void calculate(int amountSubjects) {
        super.calculate(amountSubjects);

        for (int i = 0; i < results.length; i++) {
            results[i] = (double) Math.round(results[i]);
        }
    }

}

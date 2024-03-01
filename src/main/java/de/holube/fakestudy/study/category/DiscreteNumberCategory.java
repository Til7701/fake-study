package de.holube.fakestudy.study.category;

import lombok.NonNull;

public class DiscreteNumberCategory extends NumberCategory {

    public DiscreteNumberCategory(@NonNull String name) {
        super(name);
    }

    @Override
    public void calculate(int amountSubjects) {
        super.calculate(amountSubjects);

        for (int i = 0; i < results.length; i++) {
            results[i] = (double) Math.round(results[i]);
        }
    }

}

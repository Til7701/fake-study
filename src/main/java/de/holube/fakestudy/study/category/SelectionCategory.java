package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionCategory extends Category {

    @Getter
    private final List<String> selection = new ArrayList<>();
    @Getter
    @Setter
    private int min;
    @Getter
    @Setter
    private int max;
    @Getter
    @Setter
    private String missingValue;

    private String[] results;

    @Override
    public void calculate(int amountSubjects) {
        results = new String[amountSubjects];
        int[] counter;

        do {
            counter = new int[selection.size()];
            for (int i = 0; i < results.length; i++) {
                int index;
                do {
                    index = ThreadLocalRandom.current().nextInt(selection.size());
                } while (counter[index] >= max);
                results[i] = selection.get(index);
                counter[index] = counter[index] + 1;
            }
        } while (Arrays.stream(counter).min().isPresent() && Arrays.stream(counter).min().getAsInt() < min);

    }

    @Override
    public void setMissing() {
        for (int i = 0; i < results.length; i++) {
            if (Math.random() < missingPercentage) {
                results[i] = missingValue;
            }
        }
    }

    @Override
    public String[] getStringResults() {
        return results;
    }

}

package de.holube.fakestudy.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SelectionCategory extends Category {

    private static final Random random = new Random();

    private final List<String> selection;
    private int min;
    private int max;
    private String missingValue;

    private String[] results;

    public SelectionCategory() {
        selection = new ArrayList<>();
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new String[amountSubjects];
        int[] counter;

        do {
            counter = new int[selection.size()];
            for (int i = 0; i < results.length; i++) {
                int index;
                do {
                    index = random.nextInt(selection.size());
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

    public List<String> getSelection() {
        return selection;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getMissingValue() {
        return missingValue;
    }

    public void setMissingValue(String missingValue) {
        this.missingValue = missingValue;
    }
}

package de.holube.fakestudy.category;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.exception.CalculationException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a Category, where the subject has to select an option from a list.
 */
@Getter
public class SelectionCategory extends StringCategory {

    private final List<String> options = new ArrayList<>();
    private int min;
    private int max;

    public SelectionCategory() {
        super();
    }

    public SelectionCategory(String name) {
        super(name);
    }

    public static Builder builder(String key, String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) throws CalculationException {
        results = new String[amountSubjects];
        int[] counter;

        int timeoutCounter = 0;
        do {
            counter = new int[options.size()];
            for (int i = 0; i < results.length; i++) {
                int index;
                int selectionTimeoutCounter = 0;
                do {
                    index = ThreadLocalRandom.current().nextInt(options.size());
                    selectionTimeoutCounter++;
                    if (selectionTimeoutCounter > 1000)
                        throw new CalculationException("Selection criteria cannot be met!");
                } while (counter[index] >= max);
                results[i] = options.get(index);
                counter[index] = counter[index] + 1;
                timeoutCounter++;
                if (timeoutCounter > 1000)
                    throw new CalculationException("Selection criteria cannot be met!");
            }
        } while (Arrays.stream(counter).min().isPresent() && Arrays.stream(counter).min().getAsInt() < min);
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        // AbstractCategory
        private String name = DEFAULT_NAME;
        private double missingPercentage = DEFAULT_MISSING_PERCENTAGE;
        // StringCategory
        private String missingValue = DEFAULT_MISSING_VALUE;
        // SelectionCategory
        private String[] selection = new String[0];
        private int min;
        private int max;

        public Builder selection(String... strings) {
            this.selection = strings;
            return this;
        }

        public SelectionCategory build() {
            SelectionCategory category = new SelectionCategory();
            // AbstractCategory
            category.setName(name);
            category.setMissingPercentage(missingPercentage);
            // StringCategory
            category.setMissingValue(missingValue);
            // SelectionCategory
            category.getOptions().addAll(Arrays.asList(selection));
            category.min = min;
            category.max = max;

            if (study != null)
                study.add(key, category);
            return category;
        }
    }

}

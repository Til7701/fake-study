package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.Study;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Slf4j
public class SelectionCategory extends Category<String> {

    private final List<String> selection = Collections.synchronizedList(new ArrayList<>());
    private int min;
    private int max;

    public SelectionCategory(@NonNull String name) {
        super(name);
    }

    public static Builder builder(String key, String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new String[amountSubjects];
        int[] counter;

        int timeoutCounter = 0;
        do {
            counter = new int[selection.size()];
            for (int i = 0; i < results.length; i++) {
                int index;
                int selectionTimeoutCounter = 0;
                do {
                    index = ThreadLocalRandom.current().nextInt(selection.size());
                    selectionTimeoutCounter++;
                    if (selectionTimeoutCounter > 1000) {
                        LOG.error("Selection criteria cannot be met!");
                        break;
                    }
                } while (counter[index] >= max);
                results[i] = selection.get(index);
                counter[index] = counter[index] + 1;
                timeoutCounter++;
                if (timeoutCounter > 1000) {
                    LOG.error("Selection criteria cannot be met!");
                    break;
                }
            }
        } while (Arrays.stream(counter).min().isPresent() && Arrays.stream(counter).min().getAsInt() < min);

    }

    @Override
    public String[] getStringResults() {
        return results;
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        private String name;
        private String missingValue;
        private double missingPercentage;
        @Setter(AccessLevel.NONE)
        private String[] selection;
        private int min;
        private int max;

        public Builder selection(@NonNull String... strings) {
            this.selection = strings;
            return this;
        }

        public SelectionCategory build() {
            SelectionCategory category = new SelectionCategory(name);
            category.setMissingValue(missingValue);
            category.setMissingPercentage(missingPercentage);
            category.getSelection().addAll(Arrays.asList(selection));
            category.min = min;
            category.max = max;

            study.add(key, category);
            return category;
        }
    }

}

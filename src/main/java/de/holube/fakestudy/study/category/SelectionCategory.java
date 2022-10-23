package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
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

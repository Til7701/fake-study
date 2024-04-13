package de.holube.fakestudy.study;

import de.holube.fakestudy.study.category.Category;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents a Study.
 * It holds all Categories and triggers the calculation of the fake data.
 * This class is not thread-safe.
 */
@Slf4j
@Getter
public class Study {

    private final int amountSubjects;
    private final Map<String, Category<?>> categories;

    public Study(int amountSubjects) {
        this.amountSubjects = amountSubjects;
        categories = new ConcurrentHashMap<>();
    }

    /**
     * This method triggers the calculation of all categories in alphabetical order.
     * Repeated execution of this method may result in relations between the categories to break.
     */
    public void calculate() throws CalculationException {
        List<String> keys = new ArrayList<>(categories.keySet());
        for (String key : keys) Objects.requireNonNull(key, "Key is null: " + key);
        keys.sort(String::compareTo);

        for (String key : keys) {
            LOG.debug("calculating {}", categories.get(key).getName());
            categories.get(key).calculate(amountSubjects);
        }
    }

    /**
     * This method triggers all Categories to set their missing values.
     * Repeated execution of this method will result in more than the desired amount of values to be set missing.
     */
    public void setMissing() {
        for (Category<?> category : categories.values()) {
            category.setMissing();
        }
    }

    /**
     * This method adds a Category with the given key.
     *
     * @param key      the key for the category
     * @param category the category to add
     */
    public void add(@NonNull String key, @NonNull Category<?> category) {
        categories.put(key, category);
    }

}

package de.holube.math.study;

import de.holube.math.study.category.Category;
import de.holube.math.study.exception.CalculationException;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Study.
 * It holds all Categories and triggers the calculation of the fake data.
 * This class is not thread-safe.
 */
@Getter
public class Study {

    private final int amountSubjects;
    private final Map<String, Category<?>> categories = new HashMap<>();

    public Study(int amountSubjects) {
        if (amountSubjects <= 0)
            throw new IllegalArgumentException("amount of subjects must be greater than 0");
        this.amountSubjects = amountSubjects;
    }

    /**
     * This method triggers the calculation of all categories in alphabetical order.
     * Repeated execution of this method may result in relations between the categories to break.
     *
     * @throws CalculationException if the calculation failed
     */
    public void calculate() throws CalculationException {
        List<String> keys = new ArrayList<>(categories.keySet());
        keys.sort(String::compareTo);

        for (String key : keys) {
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

    /**
     * Returns an unmodifiable copy of the categories in the study.
     *
     * @return the categories
     */
    public Map<String, Category<?>> getCategories() {
        return Map.copyOf(categories);
    }
}

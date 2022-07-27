package de.holube.fakestudy;

import de.holube.fakestudy.category.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Study {

    private final int amountSubjects;
    private final Map<String, Category> categories;
    private final List<String> entryOrder;

    public Study(int amountSubjects) {
        this.amountSubjects = amountSubjects;
        categories = new HashMap<>();
        entryOrder = new ArrayList<>();
    }

    public void calculate() {

        List<String> keys = new ArrayList<>(categories.keySet());
        keys.sort(String::compareTo);

        for (String key : keys) {
            System.out.println("calculating " + categories.get(key).getName());
            categories.get(key).calculate(amountSubjects);
        }

    }

    public void setMissing() {
        for (Category category : categories.values()) {
            category.setMissing();
        }
    }

    public void add(String key, Category category) {
        categories.put(key, category);
        entryOrder.add(key);
    }

    public List<String> getEntryOrder() {
        return entryOrder;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public int getAmountSubjects() {
        return amountSubjects;
    }
}

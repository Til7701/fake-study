package de.holube.math.study.category;

import de.holube.math.study.Study;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * This is the base class for all categories, which do not have any dependencies to other categories and do not need
 * any additional parameters.
 */
@Getter
public class StaticCategory extends StringCategory {

    public StaticCategory() {
        super();
    }

    public StaticCategory(String name) {
        super(name);
    }

    public static Builder builder(String key, String name) {
        return new Builder().key(key).name(name);
    }

    @Override
    public void calculate(int amountSubjects) {
        results = new String[amountSubjects];

        for (int i = 0; i < amountSubjects; i++) {
            results[i] = String.valueOf(i + 1);
        }
    }

    @Override
    public void setMissing() {
        // nothing to do
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private Study study;
        private String key;
        // AbstractCategory
        private String name = AbstractCategory.DEFAULT_NAME;

        public StaticCategory build() {
            StaticCategory category = new StaticCategory();
            // AbstractCategory
            category.setName(name);

            if (study != null)
                study.add(key, category);
            return category;
        }
    }

}

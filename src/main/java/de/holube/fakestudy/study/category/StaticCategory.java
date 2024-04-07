package de.holube.fakestudy.study.category;

import de.holube.fakestudy.study.Study;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
public class StaticCategory extends Category<String> {

    public StaticCategory(@NonNull String name) {
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

        public StaticCategory build() {
            StaticCategory category = new StaticCategory(name);
            study.add(key, category);
            return category;
        }
    }

}

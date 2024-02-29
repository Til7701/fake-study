package de.holube.fakestudy.study.category;

import lombok.Getter;

@Getter
public class StaticCategory extends Category<String> {

    private String[] results;

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
}

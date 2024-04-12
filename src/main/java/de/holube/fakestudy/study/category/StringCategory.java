package de.holube.fakestudy.study.category;

import lombok.NonNull;

public abstract class StringCategory extends Category<String> {


    protected StringCategory(@NonNull String name) {
        super(name);
    }

    @Override
    public String[] getStringResults() {
        return results;
    }

}

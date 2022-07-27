package de.holube.fakestudy.factory;

public abstract class AbstractCategoryFactory implements CategoryFactory {

    protected String key;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

}

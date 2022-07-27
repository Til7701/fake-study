package de.holube.fakestudy.factory;

import de.holube.fakestudy.category.Category;

public interface CategoryFactory {

    Category create();

    void setKey(String key);

    String getKey();

}

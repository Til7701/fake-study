package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;

public interface CategoryFactory {

    Category create(Study study);

    void setKey(String key);

    String getKey();

    void setMissingPercentage(VariableNumberFactory missingPercentage);

    void setName(String name);

}

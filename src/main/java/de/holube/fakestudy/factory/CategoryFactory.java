package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;

public interface CategoryFactory {

    Category create(Study study);

    String getKey();

    void setKey(String key);

    void setMissingPercentage(VariableNumberFactory missingPercentage);

    void setName(String name);

}

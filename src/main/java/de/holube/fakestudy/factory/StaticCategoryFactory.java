package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.StaticCategory;

public class StaticCategoryFactory extends AbstractCategoryFactory {

    @Override
    public Category createCategory(Study study) {
        return new StaticCategory();
    }

}

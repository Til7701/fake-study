package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.StaticCategory;

public class StaticCategoryFactory extends AbstractCategoryFactory {

    @Override
    public Category createCategory(Study study) {
        return new StaticCategory();
    }

}

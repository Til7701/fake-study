package de.holube.fakestudy.factory;

import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.StaticCategory;

public class StaticCategoryFactory extends AbstractCategoryFactory {

    @Override
    public Category create() {
        return new StaticCategory();
    }

}

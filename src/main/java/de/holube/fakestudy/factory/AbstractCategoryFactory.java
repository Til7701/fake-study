package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public abstract class AbstractCategoryFactory implements CategoryFactory {

    protected String key;
    protected String name;

    private VariableNumberFactory missingPercentage;

    public Category create(Study study) {
        LOG.debug("Creating Category {}", name);
        Category category = createCategory(study);
        category.setName(name);
        if (missingPercentage != null)
            category.setMissingPercentage(missingPercentage.create().doubleValue());
        return category;
    }

    protected abstract Category createCategory(Study study);

}

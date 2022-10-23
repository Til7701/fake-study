package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.NumberCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberCategoryFactory extends AbstractCategoryFactory {

    private DistributionFactory distributionFactory;
    private int decimalPlaces;
    private double missingValue;

    @Override
    public Category createCategory(Study study) {
        NumberCategory category = new NumberCategory();

        category.setDistribution(distributionFactory.create(study));
        category.setDecimalPlaces(decimalPlaces);
        category.setMissingValue(missingValue);

        return category;
    }

}

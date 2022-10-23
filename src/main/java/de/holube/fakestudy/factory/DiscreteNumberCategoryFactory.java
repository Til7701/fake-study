package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.DiscreteNumberCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscreteNumberCategoryFactory extends AbstractCategoryFactory {

    private DistributionFactory distributionFactory;
    private int decimalPlaces;
    private double missingValue;

    @Override
    public Category createCategory(Study study) {
        DiscreteNumberCategory category = new DiscreteNumberCategory();

        category.setDistribution(distributionFactory.create(study));
        category.setDecimalPlaces(decimalPlaces);
        category.setMissingValue(missingValue);

        return category;
    }

}

package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.CorrelationCategory;
import de.holube.fakestudy.category.NumberCategory;
import de.holube.fakestudy.util.InvertedCorrelator;
import de.holube.fakestudy.util.NormalCorrelator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorrelationCategoryFactory extends AbstractCategoryFactory {

    private int min;
    private int max;
    private DistributionFactory distributionFactory;
    private int decimalPlaces;
    private int missingValue;
    private String origin;
    @Setter(AccessLevel.NONE)
    private CorrelationDirection direction;

    @Override
    public Category createCategory(Study study) {
        NumberCategory originCategory = (NumberCategory) study.getCategories().get(origin);
        CorrelationCategory category = new CorrelationCategory(originCategory);

        category.setDistribution(distributionFactory.create(study));
        category.setMin(min);
        category.setMax(max);
        category.setMissingValue(missingValue);
        category.setDecimalPlaces(decimalPlaces);
        switch (direction) {
            case NORMAL:
                category.setCorrelator(new NormalCorrelator());
                break;
            case INVERTED:
                category.setCorrelator(new InvertedCorrelator());
        }

        return category;
    }

    public void setDirectionNormal() {
        direction = CorrelationDirection.NORMAL;
    }

    public void setDirectionInverted() {
        direction = CorrelationDirection.INVERTED;
    }

    private enum CorrelationDirection {
        NORMAL,
        INVERTED
    }

}

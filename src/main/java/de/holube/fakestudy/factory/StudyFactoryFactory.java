package de.holube.fakestudy.factory;

import de.holube.fakestudy.config.CategoryConfig;
import de.holube.fakestudy.config.DistributionConfig;
import de.holube.fakestudy.config.StudyConfig;
import de.holube.fakestudy.config.VarNumConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyFactoryFactory {

    public static StudyFactory create(StudyConfig studyConfig) {
        StudyFactoryImpl studyFactory = new StudyFactoryImpl();

        studyFactory.setAmountSubjects(studyConfig.getConstants().getAmountOfSubjects());

        List<CategoryFactory> categoryFactories = new ArrayList<>(studyConfig.getCategories().size());
        studyFactory.setCategoryFactories(categoryFactories);

        for (Map.Entry<String, CategoryConfig> categoryEntry : studyConfig.getCategories().entrySet()) {
            LOG.debug("Creating CategoryFactory: {}", categoryEntry.getValue().getName());
            categoryFactories.add(createCategoryFactory(categoryEntry.getKey(), categoryEntry.getValue()));
        }

        return studyFactory;
    }

    private static CategoryFactory createCategoryFactory(String key, CategoryConfig categoryConfig) {
        CategoryFactory categoryFactory = null;

        switch (categoryConfig.getType()) {
            case STATIC:
                categoryFactory = new StaticCategoryFactory();
                break;
            case SELECTION:
                categoryFactory = createSelectionCategoryFactory(categoryConfig);
                break;
            case NUMBER:
                categoryFactory = createNumberCategoryFactory(categoryConfig);
                break;
            case CORRELATION:
                categoryFactory = createCorrelationCategoryFactory(categoryConfig);
                break;
            case DISCRETE_NUMBER:
                categoryFactory = createDiscreteNumberCategoryFactory(categoryConfig);
                break;
        }

        if (categoryFactory == null) {
            LOG.error("categoryFactory is null");
        }
        categoryFactory.setKey(key);
        categoryFactory.setName(categoryConfig.getName());
        if (categoryConfig.getMissingPercentage() != null)
            categoryFactory.setMissingPercentage(createVariableNumberFactory(categoryConfig.getMissingPercentage()));

        return categoryFactory;
    }

    private static VariableNumberFactory createVariableNumberFactory(VarNumConfig varNumConfig) {
        VariableNumberFactory variableNumberFactory = new VariableNumberFactory();
        variableNumberFactory.setBase(varNumConfig.getBase());
        variableNumberFactory.setDiff(varNumConfig.getDiff());
        return variableNumberFactory;
    }

    private static SelectionCategoryFactory createSelectionCategoryFactory(CategoryConfig categoryConfig) {
        SelectionCategoryFactory categoryFactory = new SelectionCategoryFactory();

        categoryFactory.setSelection(Arrays.asList(categoryConfig.getOptions()));
        categoryFactory.setMin(categoryConfig.getMin());
        categoryFactory.setMax(categoryConfig.getMax());
        categoryFactory.setMissingValue(categoryConfig.getMissingValue());

        return categoryFactory;
    }

    private static NumberCategoryFactory createNumberCategoryFactory(CategoryConfig categoryConfig) {
        NumberCategoryFactory categoryFactory = new NumberCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryConfig.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryConfig.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryConfig.getMissingValue()));

        return categoryFactory;
    }

    private static DiscreteNumberCategoryFactory createDiscreteNumberCategoryFactory(CategoryConfig categoryConfig) {
        DiscreteNumberCategoryFactory categoryFactory = new DiscreteNumberCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryConfig.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryConfig.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryConfig.getMissingValue()));

        return categoryFactory;
    }

    private static CorrelationCategoryFactory createCorrelationCategoryFactory(CategoryConfig categoryConfig) {
        CorrelationCategoryFactory categoryFactory = new CorrelationCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryConfig.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryConfig.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryConfig.getMissingValue()));
        categoryFactory.setMin(categoryConfig.getMin());
        categoryFactory.setMax(categoryConfig.getMax());
        categoryFactory.setOrigin(categoryConfig.getOrigin());
        switch (categoryConfig.getCorrelate()) {
            case NORMAL:
                categoryFactory.setDirectionNormal();
                break;
            case INVERTED:
                categoryFactory.setDirectionInverted();
                break;
        }

        return categoryFactory;
    }

    private static DistributionFactory createDistributionFactory(DistributionConfig config) {
        DistributionFactory distributionFactory = new DistributionFactory();

        distributionFactory.setMin(createVariableNumberFactory(config.getMin()));
        distributionFactory.setMax(createVariableNumberFactory(config.getMax()));
        distributionFactory.setSd(createVariableNumberFactory(config.getStandardDeviation()));
        switch (config.getType().getDescriptor()) {
            case RANDOM:
                distributionFactory.setRandomType();
                break;
            case FIXED:
                distributionFactory.setFixedType(config.getType().getValue());
                break;
            case OTHER:
                distributionFactory.setOtherType(config.getType().getOther());
                break;
        }

        return distributionFactory;
    }

}

package de.holube.fakestudy.factory;

import de.holube.fakestudy.io.json.CategoryJSON;
import de.holube.fakestudy.io.json.DistributionJSON;
import de.holube.fakestudy.io.json.StudyJSON;
import de.holube.fakestudy.io.json.VariableNumberJOSN;
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

    public static StudyFactory create(StudyJSON studyJSON) {
        StudyFactory studyFactory = new StudyFactoryImpl();

        studyFactory.setAmountSubjects(studyJSON.getConstants().getAmountOfSubjects());

        List<CategoryFactory> categoryFactories = new ArrayList<>(studyJSON.getCategories().size());
        studyFactory.setCategoryFactories(categoryFactories);

        for (Map.Entry<String, CategoryJSON> categoryEntry : studyJSON.getCategories().entrySet()) {
            LOG.debug("Creating CategoryFactory: {}", categoryEntry.getValue().getName());
            categoryFactories.add(createCategoryFactory(categoryEntry.getKey(), categoryEntry.getValue()));
        }

        return studyFactory;
    }

    private static CategoryFactory createCategoryFactory(String key, CategoryJSON categoryJSON) {
        CategoryFactory categoryFactory = null;

        switch (categoryJSON.getType()) {
            case STATIC:
                categoryFactory = new StaticCategoryFactory();
                break;
            case SELECTION:
                categoryFactory = createSelectionCategoryFactory(categoryJSON);
                break;
            case NUMBER:
                categoryFactory = createNumberCategoryFactory(categoryJSON);
                break;
            case CORRELATION:
                categoryFactory = createCorrelationCategoryFactory(categoryJSON);
                break;
            case DISCRETE_NUMBER:
                categoryFactory = createDiscreteNumberCategoryFactory(categoryJSON);
                break;
        }

        if (categoryFactory == null) {
            LOG.error("categoryFactory is null");
        }
        categoryFactory.setKey(key);
        categoryFactory.setName(categoryJSON.getName());
        if (categoryJSON.getMissingPercentage() != null)
            categoryFactory.setMissingPercentage(createVariableNumberFactory(categoryJSON.getMissingPercentage()));

        return categoryFactory;
    }

    private static VariableNumberFactory createVariableNumberFactory(VariableNumberJOSN variableNumberJOSN) {
        VariableNumberFactory variableNumberFactory = new VariableNumberFactory();
        variableNumberFactory.setBase(variableNumberJOSN.getBase());
        variableNumberFactory.setDiff(variableNumberJOSN.getDiff());
        return variableNumberFactory;
    }

    private static SelectionCategoryFactory createSelectionCategoryFactory(CategoryJSON categoryJSON) {
        SelectionCategoryFactory categoryFactory = new SelectionCategoryFactory();

        categoryFactory.setSelection(Arrays.asList(categoryJSON.getOptions()));
        categoryFactory.setMin(categoryJSON.getMin());
        categoryFactory.setMax(categoryJSON.getMax());
        categoryFactory.setMissingValue(categoryJSON.getMissingValue());

        return categoryFactory;
    }

    private static NumberCategoryFactory createNumberCategoryFactory(CategoryJSON categoryJSON) {
        NumberCategoryFactory categoryFactory = new NumberCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryJSON.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryJSON.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryJSON.getMissingValue()));

        return categoryFactory;
    }

    private static DiscreteNumberCategoryFactory createDiscreteNumberCategoryFactory(CategoryJSON categoryJSON) {
        DiscreteNumberCategoryFactory categoryFactory = new DiscreteNumberCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryJSON.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryJSON.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryJSON.getMissingValue()));

        return categoryFactory;
    }

    private static CorrelationCategoryFactory createCorrelationCategoryFactory(CategoryJSON categoryJSON) {
        CorrelationCategoryFactory categoryFactory = new CorrelationCategoryFactory();

        categoryFactory.setDistributionFactory(createDistributionFactory(categoryJSON.getDistribution()));
        categoryFactory.setDecimalPlaces(categoryJSON.getDecimalPlaces());
        categoryFactory.setMissingValue(Integer.parseInt(categoryJSON.getMissingValue()));
        categoryFactory.setMin(categoryJSON.getMin());
        categoryFactory.setMax(categoryJSON.getMax());
        categoryFactory.setOrigin(categoryJSON.getOrigin());
        switch (categoryJSON.getCorrelate()) {
            case NORMAL:
                categoryFactory.setDirectionNormal();
                break;
            case INVERTED:
                categoryFactory.setDirectionInverted();
                break;
        }

        return categoryFactory;
    }

    private static DistributionFactory createDistributionFactory(DistributionJSON config) {
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

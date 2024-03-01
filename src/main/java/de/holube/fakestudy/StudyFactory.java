package de.holube.fakestudy;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.*;
import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.VariableNumber;
import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class StudyFactory {

    private final Map<String, Distribution> distributions = new ConcurrentHashMap<>();

    protected Study study;
    // TODO move to distribution implementation
    protected double distributionTypeFactor = 3;
    protected double distributionLeft = -distributionTypeFactor;
    protected double distributionCenter = 0;
    protected double distributionRight = distributionTypeFactor;

    public abstract Study create();

    //######################################
    // VariableNumber
    //######################################

    protected VariableNumber fromDiff(double base, double diff) {
        return new VariableNumber(base, diff);
    }

    //######################################
    // Distribution
    //######################################

    protected double randomDistributionType() {
        return ThreadLocalRandom.current().nextInt(3) * distributionTypeFactor;
    }

    protected double randomDistributionLeftRight() {
        return ThreadLocalRandom.current().nextBoolean() ? distributionLeft : distributionRight;
    }

    protected double otherDistributionType(@NonNull String c) {
        final double otherType = distributions.get(c).getType();
        if (otherType == 0) return randomDistributionLeftRight();
        else if (otherType < 0) return distributionRight;
        else return distributionLeft;
    }

    protected Distribution distribution(@NonNull String key, @NonNull Number min, @NonNull Number max, double type, @NonNull Number sd) {
        Distribution distribution = new Distribution(min, max, type, sd);
        distributions.put(key, distribution);
        return distribution;
    }

    protected Distribution getDistribution(@NonNull String key) {
        return distributions.get(key);
    }

    //######################################
    // Category
    //######################################

    private <R, T extends Category<R>> CategoryBuilder<R, T> cat(@NonNull String key, @NonNull T category) {
        var builder = new CategoryBuilder<>(category);
        study.add(key, category);
        return builder;
    }

    protected CategoryBuilder<String, StaticCategory> staticCat(@NonNull String key, @NonNull String name) {
        return cat(key, new StaticCategory(name));
    }

    protected CategoryBuilder<Double, NumberCategory> numberCat(@NonNull String key, @NonNull String name) {
        return cat(key, new NumberCategory(name));
    }

    protected CategoryBuilder<Double, DiscreteNumberCategory> discreteNumCat(@NonNull String key, @NonNull String name) {
        return cat(key, new DiscreteNumberCategory(name));
    }

    protected CategoryBuilder<Double, CorrelationCategory> correlationCat(@NonNull String key, @NonNull String name, @NonNull NumberCategory origin) {
        return cat(key, new CorrelationCategory(name, origin));
    }

    protected CategoryBuilder<String, SelectionCategory> selectionCat(@NonNull String key, @NonNull String name) {
        return cat(key, new SelectionCategory(name));
    }

    public record CategoryBuilder<R, T extends Category<R>>(@NonNull T category) {

        public CategoryBuilder<R, T> setMissingPercentage(double missingPercentage) {
            category.setMissingPercentage(missingPercentage);
            return this;
        }

        public T setMissingValue(@NonNull R missingValue) {
            category.setMissingValue(missingValue);
            return category;
        }

    }

}

package de.holube.fakestudy;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.CorrelationCategory;
import de.holube.fakestudy.study.category.NumberCategory;
import de.holube.fakestudy.study.category.SelectionCategory;
import de.holube.fakestudy.study.category.StaticCategory;
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

    protected StaticCategory.Builder staticCat(@NonNull String key, @NonNull String name) {
        return StaticCategory.builder(key, name)
                .study(study);
    }

    protected NumberCategory.Builder numberCat(@NonNull String key, @NonNull String name) {
        return NumberCategory.builder(key, name)
                .study(study);
    }

    protected CorrelationCategory.Builder correlationCat(@NonNull String key, @NonNull String name, @NonNull NumberCategory origin) {
        return CorrelationCategory.builder(key, name)
                .origin(origin)
                .study(study);
    }

    protected SelectionCategory.Builder selectionCat(@NonNull String key, @NonNull String name) {
        return SelectionCategory.builder(key, name)
                .study(study);
    }

}

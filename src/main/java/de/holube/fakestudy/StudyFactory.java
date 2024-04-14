package de.holube.fakestudy;

import de.holube.fakestudy.category.CorrelationCategory;
import de.holube.fakestudy.category.DistributionCategory;
import de.holube.fakestudy.category.SelectionCategory;
import de.holube.fakestudy.category.StaticCategory;
import de.holube.fakestudy.util.Distribution;
import de.holube.fakestudy.util.MinMaxDistribution;
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
    // Distribution
    //######################################

    protected double randomDistributionType() {
        return ThreadLocalRandom.current().nextInt(3) * distributionTypeFactor;
    }

    protected double randomDistributionLeftRight() {
        return ThreadLocalRandom.current().nextBoolean() ? distributionLeft : distributionRight;
    }

    protected double otherDistributionType(@NonNull String c) {
        if (distributions.get(c) instanceof MinMaxDistribution minMaxDistribution) {
            final double otherType = minMaxDistribution.getType();
            if (otherType == 0) return randomDistributionLeftRight();
            else if (otherType < 0) return distributionRight;
            else return distributionLeft;
        } else {
            throw new IllegalArgumentException("Distribution is not a MinMaxDistribution!");
        }
    }

    protected Distribution distribution(@NonNull String key, @NonNull Number min, @NonNull Number max, double type, @NonNull Number sd) {
        Distribution distribution = new MinMaxDistribution(min.doubleValue(), max.doubleValue(), type, sd.doubleValue());
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

    protected DistributionCategory.Builder numberCat(@NonNull String key, @NonNull String name) {
        return DistributionCategory.builder(key, name)
                .study(study);
    }

    protected CorrelationCategory.Builder correlationCat(@NonNull String key, @NonNull String name, @NonNull String origin) {
        return CorrelationCategory.builder(key, name)
                .originKey(origin)
                .study(study);
    }

    protected SelectionCategory.Builder selectionCat(@NonNull String key, @NonNull String name) {
        return SelectionCategory.builder(key, name)
                .study(study);
    }

}

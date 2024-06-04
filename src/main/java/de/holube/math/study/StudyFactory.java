package de.holube.math.study;

import de.holube.math.distribution.Distribution;
import de.holube.math.distribution.Distributions;
import de.holube.math.distribution.LeanedDistribution;
import de.holube.math.study.category.CorrelationCategory;
import de.holube.math.study.category.DistributionCategory;
import de.holube.math.study.category.SelectionCategory;
import de.holube.math.study.category.StaticCategory;
import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class StudyFactory {

    private final Map<String, Distribution> distributions = new ConcurrentHashMap<>();

    protected Study study;

    public abstract Study create();

    //######################################
    // Distribution
    //######################################

    protected Distribution distribution(@NonNull String key, @NonNull Number min, @NonNull Number max, double pseudoSkew, @NonNull Number sd) {
        Distribution distribution = new LeanedDistribution(min.doubleValue(), max.doubleValue(), pseudoSkew, sd.doubleValue());
        distributions.put(key, distribution);
        return distribution;
    }

    protected Distribution randomSkewDistribution(@NonNull String key, @NonNull Number min, @NonNull Number max, @NonNull Number sd) {
        Distribution distribution = Distributions.leanedRandomly(min.doubleValue(), max.doubleValue(), sd.doubleValue());
        distributions.put(key, distribution);
        return distribution;
    }

    protected Distribution otherSkewDistribution(@NonNull String key, @NonNull Number min, @NonNull Number max, String other, @NonNull Number sd) {
        Distribution distribution;
        if (distributions.get(other) instanceof LeanedDistribution leanedDistribution) {
            final double otherSkew = leanedDistribution.getPseudoSkew();
            if (otherSkew == 0)
                distribution = Distributions.leanedRandomlyLeftRight(min.doubleValue(), max.doubleValue(), sd.doubleValue());
            else
                distribution = new LeanedDistribution(min.doubleValue(), max.doubleValue(), 0, sd.doubleValue());
        } else {
            throw new IllegalArgumentException("Distribution is not a SkewedDistribution!");
        }
        distributions.put(key, distribution);
        return distribution;
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

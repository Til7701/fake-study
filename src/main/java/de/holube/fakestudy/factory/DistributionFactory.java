package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.NumberCategory;
import de.holube.fakestudy.study.util.Distribution;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Getter
@Setter
public class DistributionFactory {

    private static final Random random = new Random();

    private VariableNumberFactory min;
    private VariableNumberFactory max;
    @Setter(AccessLevel.NONE)
    private TypeDescriptor typeDescriptor;
    @Setter(AccessLevel.NONE)
    private String other;
    @Setter(AccessLevel.NONE)
    private double value;
    private VariableNumberFactory sd;

    private static double getRandomType() {
        int[] selection = new int[]{-2, 0, 2};
        return selection[random.nextInt(3)];
    }

    private static double getOtherType(double type) {
        if (type == 0) {
            int[] selection = new int[]{-2, 2};
            return selection[random.nextInt(2)];
        }
        return 0;
    }

    public Distribution create(Study study) {
        double type = 0;
        switch (typeDescriptor) {
            case FIXED:
                type = value;
                break;
            case RANDOM:
                type = getRandomType();
                break;
            case OTHER:
                Category otherCategory = study.getCategories().get(other);
                if (otherCategory instanceof NumberCategory) {
                    type = getOtherType(((NumberCategory) otherCategory).getDistribution().getType());
                } else {
                    LOG.error("Could not set Type");
                }
                break;
        }

        return new Distribution(min.create(), max.create(), type, sd.create());
    }

    public void setRandomType() {
        typeDescriptor = TypeDescriptor.RANDOM;
    }

    public void setOtherType(String otherKey) {
        other = otherKey;
        typeDescriptor = TypeDescriptor.OTHER;
    }

    public void setFixedType(double type) {
        value = type;
        typeDescriptor = TypeDescriptor.FIXED;
    }

    private enum TypeDescriptor {
        RANDOM,
        OTHER,
        FIXED
    }

}

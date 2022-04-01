package de.holube.fakestudy.old;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Distribution {
    public static final int NORMAL = 0;
    public static final int LINKS = 1;
    public static final int RECHTS = 2;

    private int type;
    private NormalDistribution normalDistribution;
    private int[] range;

    public int getType() {
        return type;
    }

    public int[] getRange() {
        return range;
    }

    public Distribution(int type, int[] range, double mean, double meanVarianz, double standardDeviation, double sdVarianz) {
        this.type = type;
        this.range = range;

        mean = MyMath.addVarianz(mean, meanVarianz);
        standardDeviation = MyMath.addVarianz(standardDeviation, sdVarianz);

        normalDistribution = new NormalDistribution(mean, standardDeviation);
    }

    public double get() {
        double sample = 0;

        do {
            sample = normalDistribution.sample();
        } while (sample < range[0] || sample > range[1]);

        return sample;
    }

    public static Distribution normal(int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        return new Distribution(NORMAL, range, (range[1] + range[0]) / 2, meanVarianz, standardDeviation, sdVarianz);
    }

    public static Distribution left(int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        return new Distribution(LINKS, range, range[0] + (0.75 * (range[1] - range[0])), meanVarianz, standardDeviation, sdVarianz);
    }

    public static Distribution right(int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        return new Distribution(RECHTS, range, range[0] + (0.25 * (range[1] - range[0])), meanVarianz, standardDeviation, sdVarianz);
    }

    public static Distribution random(int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        return Distribution.byType((int) (Math.random() * 3), range, meanVarianz, standardDeviation, sdVarianz);
    }

    public static Distribution randomSchief(int type, int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        return Distribution.byType((int) (Math.random() * 2) + 1, range, meanVarianz, standardDeviation, sdVarianz);
    }

    public static Distribution byType(int type, int[] range, double meanVarianz, double standardDeviation, double sdVarianz) {
        if (type == 0) {
            return Distribution.normal(range, meanVarianz, standardDeviation, sdVarianz);
        } else if (type == 1) {
            return Distribution.left(range, meanVarianz, standardDeviation, sdVarianz);
        } else {
            return Distribution.right(range, meanVarianz, standardDeviation, sdVarianz);
        }
    }

}

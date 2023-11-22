package de.holube.fakestudy;

import de.holube.fakestudy.study.Study;

import java.util.concurrent.ThreadLocalRandom;

public abstract class StudyFactory {

    protected double distributionTypeFactor = 3;

    public abstract Study create();

    protected double randomDistributionType() {
        return ThreadLocalRandom.current().nextInt(3) * 3.0;
    }

}

package de.holube.fakestudy;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {

    private static final int NUMBER_OF_SUBJECTS = 105;
    private static final int NUMBER_OF_STUDIES = 35;

    public static void main(String[] args) {

        List<StudyGeneratorThread> threads = new ArrayList<>();
        int amountOfStudiesPerThread = 1;

        LOG.debug("generating threads");
        for (int i = 0; i < NUMBER_OF_STUDIES; i = i + amountOfStudiesPerThread) {
            threads.add(new StudyGeneratorThread(NUMBER_OF_SUBJECTS, i, i + amountOfStudiesPerThread));
        }

        LOG.debug("generating studies");
        for (StudyGeneratorThread thread : threads) {
            thread.start();
        }
    }

}

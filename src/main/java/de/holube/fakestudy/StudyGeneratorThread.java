package de.holube.fakestudy;

@Deprecated
public class StudyGeneratorThread extends Thread {

    private final int amountOfSubjects;
    private final int from;
    private final int to;

    public StudyGeneratorThread(int amountOfSubjects, int from, int to) {
        this.amountOfSubjects = amountOfSubjects;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            StudyGenerator.generateAndSave(amountOfSubjects, i);
        }
    }

}


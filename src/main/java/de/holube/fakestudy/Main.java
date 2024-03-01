package de.holube.fakestudy;

import de.holube.fakestudy.configs.StudyFactory2023;
import de.holube.fakestudy.io.StudyExcelSaver;
import de.holube.fakestudy.io.StudyPlotSaver;
import de.holube.fakestudy.study.Study;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Main {

    private static final int AMOUNT = 30;
    private static final String EXPORT_FOLDER = "./study-export/";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LOG.info("Creating export folder...");
        File file = new File(EXPORT_FOLDER);
        LOG.debug(String.valueOf(file.mkdir()));

        final StudyFactory studyFactory = new StudyFactory2023();

        LOG.info("Creating Tasks");
        final List<Runnable> tasks = createTasks(studyFactory);

        LOG.info("Starting Threads");
        for (Runnable task : tasks) {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOG.info("done");
    }

    private static List<Runnable> createTasks(StudyFactory studyFactory) {
        final List<Runnable> tasks = new ArrayList<>(AMOUNT);
        for (int i = 0; i < AMOUNT; i++) {
            final int finalI = i;
            tasks.add(() -> {
                LOG.debug("Creating Study");
                Study study = studyFactory.create();
                LOG.debug("calculating results");
                study.calculate();
                study.setMissing();

                LOG.debug("Saving Study");
                StudyExcelSaver excelSaver = new StudyExcelSaver(study, EXPORT_FOLDER, "study" + finalI);
                excelSaver.save();
                LOG.debug("Creating plots");
                StudyPlotSaver plotSaver = new StudyPlotSaver(study, EXPORT_FOLDER, finalI);
                plotSaver.save();
                LOG.debug("Study Created");
            });
        }
        return tasks;
    }

}

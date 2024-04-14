package de.holube.fakestudy;

import de.holube.fakestudy.configs.StudyFactory2023;
import de.holube.fakestudy.io.StudyExcelSaver;
import de.holube.fakestudy.io.StudyPlotSaver;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {

    private static final int AMOUNT = 30;
    private static final String EXPORT_FOLDER = "./study-export/";

    public static void main(String[] args) throws InterruptedException {
        LOG.info("Creating export folder...");
        File file = new File(EXPORT_FOLDER);
        LOG.debug(String.valueOf(file.mkdir()));

        final StudyFactoryFactory studyFactoryFactory = StudyFactory2023::new;

        LOG.info("Creating Tasks");
        final List<Callable<Void>> tasks = createTasks(studyFactoryFactory);

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        LOG.info("Starting Tasks");
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(r -> Thread.ofPlatform()
                .name(String.valueOf(atomicInteger.getAndIncrement()))
                .unstarted(r))) {
            List<Future<Void>> futures = executor.invokeAll(tasks);
            for (var f : futures) {
                try {
                    f.get();
                } catch (ExecutionException e) {
                    LOG.error("Error while execution: ", e);
                }
            }
        }

        LOG.info("done");
    }

    private static List<Callable<Void>> createTasks(@NonNull StudyFactoryFactory studyFactoryFactory) {
        final List<Callable<Void>> tasks = new ArrayList<>(AMOUNT);
        for (int i = 0; i < AMOUNT; i++) {
            final int finalI = i;
            tasks.add(() -> {
                LOG.debug("Creating Study");
                Study study = studyFactoryFactory.create().create();
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
                return null;
            });
        }
        return tasks;
    }

    private interface StudyFactoryFactory {
        StudyFactory create();
    }

}

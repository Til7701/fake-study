package de.holube.study;

import de.holube.study.configs.StudyFactory2023;
import de.holube.study.io.StudyExcelSaver;
import de.holube.study.io.StudyPlotSaver;
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
        log.info("Creating export folder...");
        File file = new File(EXPORT_FOLDER);
        log.debug(String.valueOf(file.mkdir()));

        final StudyFactoryFactory studyFactoryFactory = StudyFactory2023::new;

        log.info("Creating Tasks");
        final List<Callable<Void>> tasks = createTasks(studyFactoryFactory);

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        log.info("Starting Tasks");
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(r -> Thread.ofPlatform()
                .name(String.valueOf(atomicInteger.getAndIncrement()))
                .unstarted(r))) {
            List<Future<Void>> futures = executor.invokeAll(tasks);
            for (var f : futures) {
                try {
                    f.get();
                } catch (ExecutionException e) {
                    log.error("Error while execution: ", e);
                }
            }
        }

        log.info("done");
    }

    private static List<Callable<Void>> createTasks(@NonNull StudyFactoryFactory studyFactoryFactory) {
        final List<Callable<Void>> tasks = new ArrayList<>(AMOUNT);
        for (int i = 0; i < AMOUNT; i++) {
            final int finalI = i;
            tasks.add(() -> {
                log.debug("Creating Study");
                Study study = studyFactoryFactory.create().create();
                log.debug("calculating results");
                study.calculate();
                study.setMissing();

                log.debug("Saving Study");
                StudyExcelSaver excelSaver = new StudyExcelSaver(study, EXPORT_FOLDER, "study" + finalI);
                excelSaver.save();
                log.debug("Creating plots");
                StudyPlotSaver plotSaver = new StudyPlotSaver(study, EXPORT_FOLDER, finalI);
                plotSaver.save();
                log.debug("Study Created");
                return null;
            });
        }
        return tasks;
    }

    private interface StudyFactoryFactory {
        StudyFactory create();
    }

}

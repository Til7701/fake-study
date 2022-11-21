package de.holube.fakestudy;

import de.holube.fakestudy.factory.StudyFactory;
import de.holube.fakestudy.factory.StudyFactoryFactory;
import de.holube.fakestudy.io.JSONFileReader;
import de.holube.fakestudy.io.StudyExcelSaver;
import de.holube.fakestudy.io.json.StudyJSON;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.ui.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        LOG.info("Welcome to the fake studies. :)");

        LOG.debug("Opening Window...");
        final Window window = new Window();

        File file = new File("study-config.json");
        String path = file.getAbsolutePath();
        LOG.info("Reading configuration from {}", path);
        final DefaultFilesCreator dfc = new DefaultFilesCreator();
        dfc.createDefaultFilesIfNotExisting(file);

        final StudyJSON config = JSONFileReader.readFile(path);
        LOG.info("Read the following configuration: {}", config.toString());

        final StudyFactory studyFactory = StudyFactoryFactory.create(config);
        LOG.debug("Factory created");

        path = path.substring(0, path.length() - 11) + "export/";
        window.setAmountOfStudies(config.getConstants().getAmountOfStudies());

        LOG.info("Creating Tasks");
        List<Callable<Void>> tasks = new ArrayList<>(config.getConstants().getAmountOfStudies());
        for (int i = 0; i < config.getConstants().getAmountOfStudies(); i++) {
            String finalPath = path;
            int finalI = i;
            tasks.add(() -> {
                LOG.debug("Creating Study");
                Study study = studyFactory.create();
                LOG.debug("calculating results");
                study.calculate();
                study.setMissing();

                LOG.debug("Saving Study");
                StudyExcelSaver excelSaver = new StudyExcelSaver(study, finalPath, "study" + finalI);
                excelSaver.save();
                LOG.debug("Study Created");
                window.studyComplete();
                return null;
            });
        }
        LOG.info("Starting Threads");
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LOG.info("Invoking Tasks...");
        executorService.invokeAll(tasks);
        LOG.info("Tasks Completed. Shutting down...");
        executorService.shutdown();
        window.close();
        LOG.info("done");
    }

}

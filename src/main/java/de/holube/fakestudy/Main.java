package de.holube.fakestudy;

import de.holube.fakestudy.config.StudyConfig;
import de.holube.fakestudy.factory.StudyFactory;
import de.holube.fakestudy.factory.StudyFactoryFactory;
import de.holube.fakestudy.io.JSONFileReader;
import de.holube.fakestudy.io.StudyExcelSaver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Main {

    public static void main(String[] args) {
        LOG.info("Welcome to the fake studies. :)");

        File file = new File("config.json");
        String path = file.getAbsolutePath();
        LOG.info("Reading configuration from {}", path);
        checkExistence(file);

        StudyConfig config = JSONFileReader.readFile(path);
        LOG.info("Read the following configuration: {}", config.toString());

        StudyFactory studyFactory = StudyFactoryFactory.create(config);
        LOG.debug("Factory created");

        path = path.substring(0, path.length() - 11) + "export/";
        for (int i = 0; i < config.getConstants().getAmountOfStudies(); i++) {
            Study study = studyFactory.create();
            study.calculate();
            study.setMissing();

            StudyExcelSaver excelSaver = new StudyExcelSaver(study, path, "study" + i);
            excelSaver.save();
        }
    }

    private static void checkExistence(File file) {
        if (!file.exists()) {
            LOG.error("File does not exist!");
            LOG.info("Press enter to close");
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LOG.info("Exiting...");
            System.exit(1);
        }
    }
}

package de.holube.fakestudy;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class DefaultFilesCreator {

    public void createDefaultFilesIfNotExisting(File configFile) {
        try {
            if (!configFile.exists()) {
                LOG.warn("File does not exist! Created new.");

                InputStream ioStream = this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("default-config.json");

                if (ioStream == null) {
                    throw new IllegalArgumentException("default-config.json is not found");
                }
                Files.copy(ioStream, Paths.get("study-config.json"));
            }
        } catch (Exception e) {
            LOG.error("could not create file", e);
        }

        LOG.info("Creating export folder...");
        File file = new File("study-export");
        LOG.debug(String.valueOf(file.mkdir()));
    }

}

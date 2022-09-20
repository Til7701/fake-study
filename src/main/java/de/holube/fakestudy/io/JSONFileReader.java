package de.holube.fakestudy.io;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import de.holube.fakestudy.config.StudyConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class JSONFileReader {

    public static StudyConfig readFile(String path) {
        StudyConfig config = new StudyConfig();

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            Gson gson = new Gson();
            config = gson.fromJson(reader, StudyConfig.class);
        } catch (IOException | JsonIOException e) {
            LOG.error("Error while reading File: ", e);
        } catch (JsonSyntaxException e) {
            LOG.error("Syntax error in config file: {}", e.getMessage());
        }
        return config;
    }

}

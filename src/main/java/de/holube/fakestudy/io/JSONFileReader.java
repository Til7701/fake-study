package de.holube.fakestudy.io;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import de.holube.fakestudy.io.json.StudyJSON;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JSONFileReader {

    public static StudyJSON readFile(String path) {
        StudyJSON config = null;

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            Gson gson = new Gson();
            config = gson.fromJson(reader, StudyJSON.class);
        } catch (IOException | JsonIOException e) {
            LOG.error("Error while reading File.", e);
        } catch (JsonSyntaxException e) {
            LOG.error("Syntax Error in config File.", e);
        }

        if (config == null) {
            LOG.error("Unknown Error: Could not read config File!");
        }
        return config;
    }

}

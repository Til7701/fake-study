package de.holube.fakestudy.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class StudyConfig {

    private ConstantsConfig constants;
    private Map<String, CategoryConfig> categories;

}

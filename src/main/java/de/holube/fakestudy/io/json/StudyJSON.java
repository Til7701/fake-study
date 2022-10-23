package de.holube.fakestudy.io.json;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@SuppressWarnings("unused")
@Getter
@ToString
public class StudyJSON {

    private ConstantsJSON constants;
    private Map<String, CategoryJSON> categories;

}

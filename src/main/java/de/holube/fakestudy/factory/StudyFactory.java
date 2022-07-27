package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;

import java.util.List;

public interface StudyFactory {

    Study create();

    List<CategoryFactory> getCategoryFactories();

}

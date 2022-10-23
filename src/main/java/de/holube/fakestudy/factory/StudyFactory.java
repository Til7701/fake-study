package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;

import java.util.List;

@SuppressWarnings("unused")
public interface StudyFactory {

    Study create();

    List<CategoryFactory> getCategoryFactories();

    void setCategoryFactories(List<CategoryFactory> factories);

    void setAmountSubjects(int amountSubjects);

}

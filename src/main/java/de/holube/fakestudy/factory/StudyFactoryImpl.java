package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.Study;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudyFactoryImpl implements StudyFactory {

    private int amountSubjects;

    private List<CategoryFactory> categoryFactories;

    @Override
    public Study create() {
        Study study = new Study(amountSubjects);

        for (CategoryFactory categoryFactory : categoryFactories) {
            study.add(categoryFactory.getKey(), categoryFactory.create(study));
        }

        return study;
    }

}

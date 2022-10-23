package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class StudyFactoryImpl implements StudyFactory {

    private int amountSubjects;

    private List<CategoryFactory> categoryFactories;

    @Override
    public Study create() {
        Study study = new Study(amountSubjects);

        categoryFactories.sort(Comparator.comparing(CategoryFactory::getKey));

        for (CategoryFactory categoryFactory : categoryFactories) {
            study.add(categoryFactory.getKey(), categoryFactory.create(study));
        }

        return study;
    }

}

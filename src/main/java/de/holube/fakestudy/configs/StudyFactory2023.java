package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.DiscreteNumberCategory;
import de.holube.fakestudy.study.category.SelectionCategory;
import de.holube.fakestudy.study.category.StaticCategory;
import de.holube.fakestudy.study.util.Distribution;
import de.holube.fakestudy.study.util.VariableNumber;

public class StudyFactory2023 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 110;
        final Study study = new Study(amountSubjects);

        final double defaultMissingBase = 0.075;
        final double defaultMissingDiff = 0.075;

        final Category aCategory = new StaticCategory();
        aCategory.setName("Name");
        study.add("A", aCategory);

        final SelectionCategory bCategory = new SelectionCategory();
        bCategory.setName("Geschlecht");
        bCategory.addOptions("m", "w");
        bCategory.setMin(40);
        bCategory.setMax(60);
        bCategory.setMissingValue("u");
        bCategory.setMissingPercentage(new VariableNumber(defaultMissingBase, defaultMissingDiff).doubleValue());
        study.add("B", bCategory);

        final DiscreteNumberCategory cCategory = new DiscreteNumberCategory();
        cCategory.setName("Alter");
        cCategory.setMissingValue(-1);
        cCategory.setMissingPercentage(new VariableNumber(defaultMissingBase, defaultMissingDiff).doubleValue());
        final Distribution cDistribution = new Distribution(
                new VariableNumber(23, 5),
                new VariableNumber(70, 7),
                randomDistributionType(),
                new VariableNumber(20, 5)
        );
        cCategory.setDistribution(cDistribution);
        study.add("C", cCategory);

        return study;
    }

}

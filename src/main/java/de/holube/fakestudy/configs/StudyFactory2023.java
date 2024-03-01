package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.NumberCategory;
import de.holube.fakestudy.study.util.correlate.Correlators;

public class StudyFactory2023 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 110;
        study = new Study(amountSubjects);

        final double defaultMissingBase = 0.075;
        final double defaultMissingDiff = 0.025;

        staticCat("A", "Name");

        selectionCat("B", "Gechlecht")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue("u")
                .addOptions("m", "w")
                .setMin(40)
                .setMax(60);

        discreteNumCat("C", "Alter")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("C",
                        fromDiff(23, 5),
                        fromDiff(70, 7),
                        randomDistributionType(),
                        fromDiff(20, 5)
                ))
                .setDecimalPlaces(0);

        NumberCategory dCategory = numberCat("D", "Fitness")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("D",
                        fromDiff(100, 40),
                        fromDiff(3000, 600),
                        otherDistributionType("C"),
                        fromDiff(1000, 200)
                ));
        dCategory.setDecimalPlaces(1);

        correlationCat("E", "Lesegeschwindigkeit", dCategory)
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setCorrelator(Correlators.NORMAL_CORRELATOR)
                .setMin(10)
                .setMax(1000)
                .setDistribution(distribution("E",
                        fromDiff(-500, 100),
                        fromDiff(500, 100),
                        0,
                        fromDiff(300, 75)
                ))
                .setDecimalPlaces(0);

        selectionCat("F", "Farbe des Kleinbusses")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue("u")
                .addOptions("s", "b", "r", "g", "l")
                .setMin(5)
                .setMax(40);

        selectionCat("G", "Nationalität")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue("u")
                .addOptions("DE", "FR", "IT", "LV", "ES", "PO")
                .setMin(5)
                .setMax(40);

        selectionCat("H", "Anreise")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue("u")
                .addOptions("F", "B", "Z", "A")
                .setMin(5)
                .setMax(40);

        numberCat("I", "Wasserkonsum in Rom")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("I",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .setDecimalPlaces(2);

        numberCat("J", "Wasserkonsum in Berlin")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("J",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .setDecimalPlaces(2);

        numberCat("K", "Wasserkonsum in Paris")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("K",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .setDecimalPlaces(2);

        numberCat("L", "Punktzahl bei Wissenstest für Rom")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("L",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("I"),
                        fromDiff(12, 5)
                ))
                .setDecimalPlaces(0);

        numberCat("M", "Punktzahl bei Wissenstest für Berlin")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("M",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("J"),
                        fromDiff(12, 5)
                ))
                .setDecimalPlaces(0);

        numberCat("N", "Punktzahl bei Wissenstest für Paris")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("N",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("K"),
                        fromDiff(12, 5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("O", "Bewertung Ausreichen der Zeit bei den Stationen in Rom")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("O",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("P", "Bewertung Ausreichen der Zeit bei den Stationen in Berlin")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("P",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("Q", "Bewertung Ausreichen der Zeit bei den Stationen in Paris")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("Q",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("R", "Bewertung des Essens in Rom")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("R",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("O"),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("S", "Bewertung des Essens in Berlin")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("S",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("P"),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        discreteNumCat("T", "Bewertung des Essens in Paris")
                .setMissingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .setMissingValue(-1d)
                .setDistribution(distribution("T",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("Q"),
                        fromDiff(2, 0.5)
                ))
                .setDecimalPlaces(0);

        return study;
    }

}

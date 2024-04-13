package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.correlate.Correlators;

import static de.holube.fakestudy.study.util.VariableNumber.fromDiff;

public class StudyFactory2023 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 110;
        study = new Study(amountSubjects);

        final double defaultMissingBase = 0.075;
        final double defaultMissingDiff = 0.025;

        staticCat("A", "Name")
                .build();

        selectionCat("B", "Gechlecht")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("m", "w")
                .min(40)
                .max(60)
                .build();

        numberCat("C", "Alter")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("C",
                        fromDiff(23, 5),
                        fromDiff(70, 7),
                        randomDistributionType(),
                        fromDiff(20, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("D", "Fitness")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("D",
                        fromDiff(100, 40),
                        fromDiff(3000, 600),
                        otherDistributionType("C"),
                        fromDiff(1000, 200)
                ))
                .decimalPlaces(1)
                .build();

        correlationCat("E", "Lesegeschwindigkeit", "D")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .correlator(Correlators.NORMAL_CORRELATOR)
                .min(10)
                .max(1000)
                .distribution(distribution("E",
                        fromDiff(-500, 100),
                        fromDiff(500, 100),
                        0,
                        fromDiff(300, 75)
                ))
                .decimalPlaces(0)
                .build();

        selectionCat("F", "Farbe des Kleinbusses")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("s", "b", "r", "g", "l")
                .min(5)
                .max(40)
                .build();

        selectionCat("G", "Nationalität")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("DE", "FR", "IT", "LV", "ES", "PO")
                .min(5)
                .max(40)
                .build();

        selectionCat("H", "Anreise")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("F", "B", "Z", "A")
                .min(5)
                .max(40)
                .build();

        numberCat("I", "Wasserkonsum in Rom")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("I",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .decimalPlaces(2)
                .build();

        numberCat("J", "Wasserkonsum in Berlin")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("J",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .decimalPlaces(2)
                .build();

        numberCat("K", "Wasserkonsum in Paris")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("K",
                        fromDiff(0, 0),
                        fromDiff(2, 0),
                        randomDistributionType(),
                        fromDiff(0.5, 0.2)
                ))
                .decimalPlaces(2)
                .build();

        numberCat("L", "Punktzahl bei Wissenstest für Rom")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("L",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("I"),
                        fromDiff(12, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("M", "Punktzahl bei Wissenstest für Berlin")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("M",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("J"),
                        fromDiff(12, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("N", "Punktzahl bei Wissenstest für Paris")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("N",
                        fromDiff(5, 5),
                        fromDiff(49, 1),
                        otherDistributionType("K"),
                        fromDiff(12, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("O", "Bewertung Ausreichen der Zeit bei den Stationen in Rom")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("O",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("P", "Bewertung Ausreichen der Zeit bei den Stationen in Berlin")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("P",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("Q", "Bewertung Ausreichen der Zeit bei den Stationen in Paris")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("Q",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        randomDistributionType(),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("R", "Bewertung des Essens in Rom")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("R",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("O"),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("S", "Bewertung des Essens in Berlin")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("S",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("P"),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("T", "Bewertung des Essens in Paris")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("T",
                        fromDiff(1, 0),
                        fromDiff(5, 0),
                        otherDistributionType("Q"),
                        fromDiff(2, 0.5)
                ))
                .decimalPlaces(0)
                .build();

        return study;
    }

}

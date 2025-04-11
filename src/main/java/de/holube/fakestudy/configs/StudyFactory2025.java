package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.correlate.Correlators;

public class StudyFactory2025 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 275;
        study = new Study(amountSubjects);

        final double defaultMissingBase = 0.075;
        final double defaultMissingDiff = 0.025;

        staticCat("A", "Name")
                .build();

        selectionCat("B", "Gechlecht")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("m", "w")
                .min((amountSubjects / 2) - 10)
                .max((amountSubjects / 2) + 10)
                .build();

        numberCat("C", "Alter")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("C",
                        fromDiff(23, 5),
                        fromDiff(73, 7),
                        randomDistributionType(),
                        fromDiff(15, 2)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("D", "Geschmackssensitivität")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("D",
                        fromDiff(25, 5),
                        fromDiff(90, 10),
                        otherDistributionType("C"),
                        fromDiff(15, 5)
                ))
                .decimalPlaces(1)
                .build();

        correlationCat("E", "Einkommen pro Monat (in Euro)", "C")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .correlator(Correlators.NORMAL_CORRELATOR)
                .min(500)
                .max(10000)
                .distribution(distribution("E",
                        fromDiff(-5000, 1000),
                        fromDiff(5000, 1000),
                        0,
                        fromDiff(2000, 500)
                ))
                .decimalPlaces(0)
                .build();

        selectionCat("F", "Anreise zu den Eisdielen")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("S", "F", "R", "A", "B")
                .min(10)
                .max(100)
                .build();

        selectionCat("G", "Lieblings-Eissorte")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("S", "V", "Z", "E", "H", "M")
                .min(10)
                .max(100)
                .build();

        selectionCat("H", "Häufigkeit des Eisessens")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("T", "W", "M", "S")
                .min(10)
                .max(100)
                .build();

        numberCat("I", "Preis einer Eiskugel bei BAR (Einheit Euro)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("I",
                        fromDiff(1, 0.9),
                        fromDiff(4, 1),
                        randomDistributionType(),
                        fromDiff(1, 0.5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("J", "Preis einer Eiskugel bei MANU (Einheit Euro)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("J",
                        fromDiff(1, 0.9),
                        fromDiff(4, 1),
                        randomDistributionType(),
                        fromDiff(1, 0.5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("K", "Preis einer Eiskugel bei CAFE (Einheit Euro)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("K",
                        fromDiff(1, 0.9),
                        fromDiff(4, 1),
                        randomDistributionType(),
                        fromDiff(1, 0.5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("L", "Gewicht einer Eiskugel bei BAR (Einheit g)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("L",
                        fromDiff(35, 5),
                        fromDiff(140, 10),
                        otherDistributionType("I"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("M", "Gewicht einer Eiskugel bei MANU (Einheit g)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("M",
                        fromDiff(35, 5),
                        fromDiff(140, 10),
                        otherDistributionType("J"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("N", "Gewicht einer Eiskugel bei CAFE (Einheit g)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("N",
                        fromDiff(35, 5),
                        fromDiff(140, 10),
                        otherDistributionType("K"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("O", "Bewertung des Geschmacks bei BAR")
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

        numberCat("P", "Bewertung des Geschmacks bei MANU")
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

        numberCat("Q", "Bewertung des Geschmacks bei CAFE")
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

        numberCat("R", "Bewertung der Beschaffenheit (Cremigkeit und Abschmelzverhalten) bei BAR")
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

        numberCat("S", "Bewertung der Beschaffenheit (Cremigkeit und Abschmelzverhalten) bei MANU")
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

        numberCat("T", "Bewertung der Beschaffenheit (Cremigkeit und Abschmelzverhalten) bei CAFE")
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

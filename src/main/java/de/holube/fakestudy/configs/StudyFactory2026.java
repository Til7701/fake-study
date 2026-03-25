package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.correlate.Correlators;

public class StudyFactory2026 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 275;
        study = new Study(amountSubjects);

        final double defaultMissingBase = 0.075;
        final double defaultMissingDiff = 0.025;

        staticCat("A", "Name")
                .build();

        selectionCat("B", "Geschlecht")
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

        numberCat("D", "Body-Mass-Index")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("D",
                        fromDiff(18, 3),
                        fromDiff(45, 5),
                        otherDistributionType("C"),
                        fromDiff(8, 2)
                ))
                .decimalPlaces(1)
                .build();

        correlationCat("E", "Schokoladenkonsum pro Woche (in g)", "D")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .correlator(Correlators.NORMAL_CORRELATOR)
                .min(0)
                .max(1000)
                .distribution(distribution("E",
                        fromDiff(-500, 50),
                        fromDiff(500, 50),
                        0,
                        fromDiff(200, 20)
                ))
                .decimalPlaces(0)
                .build();

        selectionCat("F", "Präferenztyp")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("B", "M", "W", "N", "K")
                .min(10)
                .max(100)
                .build();

        selectionCat("G", "Am häufigsten genutzter Lebensmittelhändler")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("A", "C", "E", "F", "N", "R")
                .min(10)
                .max(100)
                .build();

        selectionCat("H", "Tabak- und/oder Alkoholkonsum")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("T", "W", "M", "N")
                .min(10)
                .max(100)
                .build();

        numberCat("I", "Kakaoanteil der Schokolade bei DIS (in %)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(999d)
                .distribution(distribution("I",
                        fromDiff(7, 7),
                        fromDiff(83, 7),
                        randomDistributionType(),
                        fromDiff(20, 5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("J", "Kakaoanteil der Schokolade bei MAR (in %)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(999d)
                .distribution(distribution("J",
                        fromDiff(7, 7),
                        fromDiff(83, 7),
                        randomDistributionType(),
                        fromDiff(20, 5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("K", "Kakaoanteil der Schokolade bei PRE (in %)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(999d)
                .distribution(distribution("K",
                        fromDiff(7, 7),
                        fromDiff(83, 7),
                        randomDistributionType(),
                        fromDiff(20, 5)
                ))
                .decimalPlaces(1)
                .build();

        numberCat("L", "Schmelzpunkt der Schokolade bei DIS (Einheit °C)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("L",
                        fromDiff(23, 3),
                        fromDiff(52, 3),
                        otherDistributionType("I"),
                        fromDiff(10, 3)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("M", "Schmelzpunkt der Schokolade bei MAR (Einheit °C)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("M",
                        fromDiff(23, 3),
                        fromDiff(52, 3),
                        otherDistributionType("J"),
                        fromDiff(10, 3)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("N", "Schmelzpunkt der Schokolade bei PRE (Einheit °C)")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("N",
                        fromDiff(23, 3),
                        fromDiff(52, 3),
                        otherDistributionType("K"),
                        fromDiff(10, 3)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("O", "Bewertung des Geschmacks bei DIS")
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

        numberCat("P", "Bewertung des Geschmacks bei MAR")
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

        numberCat("Q", "Bewertung des Geschmacks bei PRE")
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

        numberCat("R", "Bewertung der Kaufbereitschaft bei DIS")
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

        numberCat("S", "Bewertung der Kaufbereitschaft bei MAR")
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

        numberCat("T", "Bewertung der Kaufbereitschaft bei PRE")
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

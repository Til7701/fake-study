package de.holube.fakestudy.configs;

import de.holube.fakestudy.StudyFactory;
import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.util.correlate.Correlators;

public class StudyFactory2024 extends StudyFactory {

    @Override
    public Study create() {
        final int amountSubjects = 250;
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

        numberCat("D", "Reaktionsfähigkeit")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("D",
                        fromDiff(100, 40),
                        fromDiff(900, 100),
                        otherDistributionType("C"),
                        fromDiff(300, 100)
                ))
                .decimalPlaces(1)
                .build();

        correlationCat("E", "Hörverlust als PTA4 (in dB HL)", "D")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-999d)
                .correlator(Correlators.NORMAL_CORRELATOR)
                .min(-10)
                .max(120)
                .distribution(distribution("E",
                        fromDiff(-50, 10),
                        fromDiff(50, 10),
                        0,
                        fromDiff(40, 10)
                ))
                .decimalPlaces(0)
                .build();

        selectionCat("F", "Farbe des Bildschirmhintergrundes")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("w", "s", "b", "r", "g")
                .min(5)
                .max(40)
                .build();

        selectionCat("G", "Muttersprache")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("D", "F", "E", "T", "S", "A")
                .min(5)
                .max(40)
                .build();

        selectionCat("H", "Kopfhörer")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue("u")
                .selection("S", "J", "O", "A")
                .min(5)
                .max(40)
                .build();

        numberCat("I", "VRT bei ISTS")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("I",
                        fromDiff(100, 100),
                        fromDiff(1300, 200),
                        randomDistributionType(),
                        fromDiff(500, 100)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("J", "VRT bei ICRA5")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("J",
                        fromDiff(100, 100),
                        fromDiff(1300, 200),
                        randomDistributionType(),
                        fromDiff(500, 100)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("K", "VRT bei ICRA5")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("K",
                        fromDiff(100, 100),
                        fromDiff(1300, 200),
                        randomDistributionType(),
                        fromDiff(500, 100)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("L", "Prozentuales Sprachverstehen bei ISTS")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("L",
                        fromDiff(5, 5),
                        fromDiff(90, 10),
                        otherDistributionType("I"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("M", "Prozentuales Sprachverstehen bei ICRA")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("M",
                        fromDiff(5, 5),
                        fromDiff(90, 10),
                        otherDistributionType("J"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("N", "Prozentuales Sprachverstehen bei MAND")
                .missingPercentage(fromDiff(defaultMissingBase, defaultMissingDiff).doubleValue())
                .missingValue(-1d)
                .distribution(distribution("N",
                        fromDiff(5, 5),
                        fromDiff(90, 10),
                        otherDistributionType("K"),
                        fromDiff(30, 5)
                ))
                .decimalPlaces(0)
                .build();

        numberCat("O", "Bewertung der Höranstrengung für ISTS")
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

        numberCat("P", "Bewertung der Höranstrengung für ICRA")
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

        numberCat("Q", "Bewertung der Höranstrengung für MAND")
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

        numberCat("R", "Bewertung von subjektivem Sprachverstehen für ISTS")
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

        numberCat("S", "Bewertung von subjektivem Sprachverstehen für ICRA")
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

        numberCat("T", "Bewertung von subjektivem Sprachverstehen für MAND")
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

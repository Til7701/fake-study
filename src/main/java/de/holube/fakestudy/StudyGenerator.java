package de.holube.fakestudy;

import java.util.Random;

public class StudyGenerator {

    private static final double DEFAULT_MISSING_PERCENTAGE_BASE = 0.075;
    private static final double DEFAULT_MISSING_PERCENTAGE_DIFF = 0.025;
    private static final Random random = new Random();

    public static Study generateAndSave(int numberOfSubjects, int index) {
        System.out.println("[{" + index + "}] generating study");
        Study study = generate(numberOfSubjects);

        System.out.println("[{" + index + "}] calculating study");
        study.calculate();

        System.out.println("[{" + index + "}] set missing in studies");
        study.setMissing();

        System.out.println("[{" + index + "}] saving study");
        StudyExcelSaver studyExcelSaver = new StudyExcelSaver(study, "", "study" + index);
        studyExcelSaver.save();

        return study;
    }

    public static Study generate(int numberOFSubjects) {
        Study study = new Study(numberOFSubjects);

        StaticCategory a = new StaticCategory();
        study.add("A", a);
        a.setName("Name");

        SelectionCategory b = new SelectionCategory();
        study.add("B", b);
        b.setName("Geschlecht");
        b.getSelection().add("m");
        b.getSelection().add("w");
        b.setMin((numberOFSubjects / 2) - 10);
        b.setMax((numberOFSubjects / 2) + 10);
        b.setMissingValue("u");
        b.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory c = new NumberCategory();
        study.add("C", c);
        c.setName("Alter");
        Distribution cDistribution = new Distribution(
                new VariableNumber(28, 10),
                new VariableNumber(70, 10),
                getRandomType(),
                new VariableNumber(10, 10)
        );
        c.setDistribution(cDistribution);
        c.setDecimalPlaces(0);
        c.setMissingValue(-1);
        c.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory d = new NumberCategory();
        study.add("D", d);
        d.setName("Reaktionsfähigkeit");
        Distribution dDistribution = new Distribution(
                new VariableNumber(100, 0),
                new VariableNumber(1000, 0),
                getOtherType(cDistribution.getType()),
                new VariableNumber(200, 100)
        );
        d.setDistribution(dDistribution);
        d.setDecimalPlaces(1);
        d.setMissingValue(-1);
        d.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        CorrelationCategory e = new CorrelationCategory(d);
        study.add("E", e);
        e.setName("IQ");
        e.setMin(70);
        e.setMax(130);
        Distribution eDistribution = new Distribution(
                new VariableNumber(-25, 5),
                new VariableNumber(25, 5),
                0,
                new VariableNumber(20, 5)
        );
        e.setDistribution(eDistribution);
        e.setDecimalPlaces(0);
        e.setCorrelator(new InvertedCorrelator());
        e.setMissingValue(-1);
        e.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        SelectionCategory f = new SelectionCategory();
        study.add("F", f);
        f.setName("Farbe des Smartphones");
        f.getSelection().add("w");
        f.getSelection().add("s");
        f.getSelection().add("b");
        f.getSelection().add("r");
        f.getSelection().add("g");
        f.setMin(5);
        f.setMax(40);
        f.setMissingValue("u");
        f.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        SelectionCategory g = new SelectionCategory();
        study.add("G", g);
        g.setName("Nationalität");
        g.getSelection().add("DE");
        g.getSelection().add("FR");
        g.getSelection().add("GB");
        g.getSelection().add("IT");
        g.getSelection().add("NL");
        g.getSelection().add("NO");
        g.setMin(5);
        g.setMax(40);
        g.setMissingValue("u");
        g.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        SelectionCategory h = new SelectionCategory();
        study.add("H", h);
        h.setName("Betriebssystem");
        h.getSelection().add("A");
        h.getSelection().add("I");
        h.getSelection().add("W");
        h.getSelection().add("U");
        h.setMin(5);
        h.setMax(40);
        h.setMissingValue("u");
        h.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory i = new NumberCategory();
        study.add("I", i);
        i.setName("Spieldauer bei CandyCrush");
        Distribution iDistribution = new Distribution(
                new VariableNumber(60, 0),
                new VariableNumber(300, 0),
                getRandomType(),
                new VariableNumber(70, 20)
        );
        i.setDistribution(iDistribution);
        i.setDecimalPlaces(1);
        i.setMissingValue(-1);
        i.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory j = new NumberCategory();
        study.add("J", j);
        j.setName("Spieldauer bei Tetris");
        Distribution jDistribution = new Distribution(
                new VariableNumber(60, 0),
                new VariableNumber(300, 0),
                getRandomType(),
                new VariableNumber(70, 20)
        );
        j.setDistribution(jDistribution);
        j.setDecimalPlaces(1);
        j.setMissingValue(-1);
        j.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory k = new NumberCategory();
        study.add("K", k);
        k.setName("Spieldauer bei AngryBirds");
        Distribution kDistribution = new Distribution(
                new VariableNumber(60, 0),
                new VariableNumber(300, 0),
                getRandomType(),
                new VariableNumber(70, 20)
        );
        k.setDistribution(kDistribution);
        k.setDecimalPlaces(1);
        k.setMissingValue(-1);
        k.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory l = new NumberCategory();
        study.add("L", l);
        l.setName("Prozentualer Spielfortschritt bei CandyCrush");
        Distribution lDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(100, 0),
                getOtherType(iDistribution.getType()),
                new VariableNumber(30, 10)
        );
        l.setDistribution(lDistribution);
        l.setDecimalPlaces(2);
        l.setMissingValue(-1);
        l.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory m = new NumberCategory();
        study.add("M", m);
        m.setName("Prozentualer Spielfortschritt bei Tetris");
        Distribution mDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(100, 0),
                getOtherType(jDistribution.getType()),
                new VariableNumber(30, 10)
        );
        m.setDistribution(mDistribution);
        m.setDecimalPlaces(2);
        m.setMissingValue(-1);
        m.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        NumberCategory n = new NumberCategory();
        study.add("M", n);
        n.setName("Prozentualer Spielfortschritte bei AngryBirds");
        Distribution nDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(100, 0),
                getOtherType(kDistribution.getType()),
                new VariableNumber(30, 10)
        );
        n.setDistribution(nDistribution);
        n.setDecimalPlaces(2);
        n.setMissingValue(-1);
        n.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory o = new DiscreteNumberCategory();
        study.add("O", o);
        o.setName("Bewertung des Spielvergnügens für CandyCrush");
        Distribution oDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        o.setDistribution(oDistribution);
        o.setDecimalPlaces(0);
        o.setMissingValue(-1);
        o.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory p = new DiscreteNumberCategory();
        study.add("P", p);
        p.setName("Bewertung des Spielvergnügens für Tetris");
        Distribution pDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        p.setDistribution(pDistribution);
        p.setDecimalPlaces(0);
        p.setMissingValue(-1);
        p.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory q = new DiscreteNumberCategory();
        study.add("Q", q);
        q.setName("Bewertung des Spielvergnügens für AngryBirds");
        Distribution qDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        q.setDistribution(qDistribution);
        q.setDecimalPlaces(0);
        q.setMissingValue(-1);
        q.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory r = new DiscreteNumberCategory();
        study.add("R", r);
        r.setName("Bewertung von Grafik und Sound für CandyCrush");
        Distribution rDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(oDistribution.getType()),
                new VariableNumber(3, 1)
        );
        r.setDistribution(rDistribution);
        r.setDecimalPlaces(0);
        r.setMissingValue(-1);
        r.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory s = new DiscreteNumberCategory();
        study.add("S", s);
        s.setName("Bewertung von Grafik und Sound für Tetris");
        Distribution sDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(pDistribution.getType()),
                new VariableNumber(3, 1)
        );
        s.setDistribution(sDistribution);
        s.setDecimalPlaces(0);
        s.setMissingValue(-1);
        s.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        DiscreteNumberCategory t = new DiscreteNumberCategory();
        study.add("T", t);
        t.setName("Bewertung von Grafik und Sound für AngryBirds");
        Distribution tDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(oDistribution.getType()),
                new VariableNumber(3, 1)
        );
        t.setDistribution(tDistribution);
        t.setDecimalPlaces(0);
        t.setMissingValue(-1);
        t.setMissingPercentage(new VariableNumber(DEFAULT_MISSING_PERCENTAGE_BASE, DEFAULT_MISSING_PERCENTAGE_DIFF).doubleValue());

        return study;
    }

    private static double getRandomType() {
        int[] selection = new int[]{-2, 0, 2};
        return selection[random.nextInt(3)];
    }

    private static double getOtherType(double type) {
        if (type == 0) {
            int[] selection = new int[]{-2, 2};
            return selection[random.nextInt(2)];
        }
        return 0;
    }

}

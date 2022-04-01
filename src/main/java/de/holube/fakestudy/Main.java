package de.holube.fakestudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random();
    private static final int NUMBER_OF_SUBJECTS = 82;
    private static final int NUMBER_OF_STUDIES = 5;

    private static final List<Study> studies = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < NUMBER_OF_STUDIES; i++) {
            studies.add(generateStudy());
        }

        for (Study study : studies) {
            study.calculate();
        }

        for (int i = 0; i < NUMBER_OF_STUDIES; i++) {
            StudyExcelSaver studyExcelSaver = new StudyExcelSaver(studies.get(i), "", "study" + i);
            studyExcelSaver.save();
        }
    }

    private static Study generateStudy() {
        Study study = new Study(NUMBER_OF_SUBJECTS);

        StaticCategory a = new StaticCategory();
        study.add("A", a);
        a.setName("Name");

        SelectionCategory b = new SelectionCategory();
        study.add("B", b);
        b.setName("Geschlecht");
        b.getSelection().add("m");
        b.getSelection().add("w");
        b.setMin((NUMBER_OF_SUBJECTS / 2) - 10);
        b.setMax((NUMBER_OF_SUBJECTS / 2) + 10);

        NumberCategory c = new NumberCategory();
        study.add("C", c);
        c.setName("Alter");
        Distribution cDistribution = new Distribution(
                new VariableNumber(25, 7),
                new VariableNumber(80, 10),
                getRandomType(),
                new VariableNumber(20, 5)
        );
        c.setDistribution(cDistribution);
        c.setDecimalPlaces(0);

        NumberCategory d = new NumberCategory();
        study.add("D", d);
        d.setName("Fitness");
        Distribution dDistribution = new Distribution(
                new VariableNumber(0, 0),
                new VariableNumber(100, 0),
                getOtherType(cDistribution.getType()),
                new VariableNumber(20, 5)
        );
        d.setDistribution(dDistribution);
        d.setDecimalPlaces(1);

        CorrelationCategory e = new CorrelationCategory(d);
        study.add("E", e);
        e.setName("Ausgangsgewicht");
        e.setMin(50);
        e.setMax(150);
        Distribution eDistribution = new Distribution(
                new VariableNumber(-25, 5),
                new VariableNumber(25, 5),
                0,
                new VariableNumber(20, 5)
        );
        e.setDistribution(eDistribution);
        e.setDecimalPlaces(0);

        SelectionCategory f = new SelectionCategory();
        study.add("F", f);
        f.setName("Farbe des Kabinendesigns");
        f.getSelection().add("w");
        f.getSelection().add("e");
        f.getSelection().add("b");
        f.getSelection().add("r");
        f.getSelection().add("g");
        f.setMin(5);
        f.setMax(40);

        SelectionCategory g = new SelectionCategory();
        study.add("G", g);
        g.setName("Wohnort");
        g.getSelection().add("HH");
        g.getSelection().add("NI");
        g.getSelection().add("HB");
        g.getSelection().add("SH");
        g.setMin(5);
        g.setMax(40);

        SelectionCategory h = new SelectionCategory();
        study.add("H", h);
        h.setName("Berufsbereich");
        h.getSelection().add("P");
        h.getSelection().add("V");
        h.getSelection().add("D");
        h.getSelection().add("V");
        h.getSelection().add("L");
        h.getSelection().add("R");
        h.getSelection().add("A");
        h.setMin(5);
        h.setMax(40);

        NumberCategory i = new NumberCategory();
        study.add("I", i);
        i.setName("Mittlere Herzfrequenz bei Heavy-Metal-Kreuzfahrt");
        Distribution iDistribution = new Distribution(
                new VariableNumber(50, 0),
                new VariableNumber(200, 0),
                getRandomType(),
                new VariableNumber(70, 10)
        );
        i.setDistribution(iDistribution);
        i.setDecimalPlaces(2);

        NumberCategory j = new NumberCategory();
        study.add("J", j);
        j.setName("Mittlere Herzfrequenz bei Schlager-Kreuzfahrt");
        Distribution jDistribution = new Distribution(
                new VariableNumber(50, 0),
                new VariableNumber(200, 0),
                getRandomType(),
                new VariableNumber(70, 10)
        );
        j.setDistribution(jDistribution);
        j.setDecimalPlaces(2);

        NumberCategory k = new NumberCategory();
        study.add("K", k);
        k.setName("Mittlere Herzfrequenz bei HipHop-Kreuzfahrt");
        Distribution kDistribution = new Distribution(
                new VariableNumber(50, 0),
                new VariableNumber(200, 0),
                getRandomType(),
                new VariableNumber(70, 10)
        );
        k.setDistribution(kDistribution);
        k.setDecimalPlaces(2);

        NumberCategory l = new NumberCategory();
        study.add("L", l);
        l.setName("Gewichtsänderung bei Heavy-Metal-Kreuzfahrt");
        Distribution lDistribution = new Distribution(
                new VariableNumber(2, 0),
                new VariableNumber(4, 0),
                getOtherType(iDistribution.getType()),
                new VariableNumber(70, 10)
        );
        l.setDistribution(lDistribution);
        l.setDecimalPlaces(2);

        NumberCategory m = new NumberCategory();
        study.add("M", m);
        m.setName("Gewichtsänderung bei Schlager-Kreuzfahrt");
        Distribution mDistribution = new Distribution(
                new VariableNumber(2, 0),
                new VariableNumber(4, 0),
                getOtherType(jDistribution.getType()),
                new VariableNumber(70, 10)
        );
        m.setDistribution(mDistribution);
        m.setDecimalPlaces(2);

        NumberCategory n = new NumberCategory();
        study.add("M", n);
        n.setName("Gewichtsänderung bei HipHop-Kreuzfahrt");
        Distribution nDistribution = new Distribution(
                new VariableNumber(2, 0),
                new VariableNumber(4, 0),
                getOtherType(kDistribution.getType()),
                new VariableNumber(70, 10)
        );
        n.setDistribution(nDistribution);
        n.setDecimalPlaces(2);

        DiscreteNumberCategory o = new DiscreteNumberCategory();
        study.add("O", o);
        o.setName("Bewertung der Heavy-Metal-Musik");
        Distribution oDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        o.setDistribution(oDistribution);
        o.setDecimalPlaces(0);

        DiscreteNumberCategory p = new DiscreteNumberCategory();
        study.add("P", p);
        p.setName("Bewertung der Schlager-Musik");
        Distribution pDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        p.setDistribution(pDistribution);
        p.setDecimalPlaces(0);

        DiscreteNumberCategory q = new DiscreteNumberCategory();
        study.add("Q", q);
        q.setName("Bewertung der HipHop-Musik");
        Distribution qDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getRandomType(),
                new VariableNumber(3, 1)
        );
        q.setDistribution(qDistribution);
        q.setDecimalPlaces(0);

        DiscreteNumberCategory r = new DiscreteNumberCategory();
        study.add("R", r);
        r.setName("Bewertung des Essens auf Heavy-Metal-Kreuzfahrt");
        Distribution rDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(oDistribution.getType()),
                new VariableNumber(3, 1)
        );
        r.setDistribution(rDistribution);
        r.setDecimalPlaces(0);

        DiscreteNumberCategory s = new DiscreteNumberCategory();
        study.add("S", s);
        s.setName("Bewertung des Essens auf Schlager-Kreuzfahrt");
        Distribution sDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(pDistribution.getType()),
                new VariableNumber(3, 1)
        );
        s.setDistribution(sDistribution);
        s.setDecimalPlaces(0);

        DiscreteNumberCategory t = new DiscreteNumberCategory();
        study.add("T", t);
        t.setName("Bewertung des Essens auf HipHop-Kreuzfahrt");
        Distribution tDistribution = new Distribution(
                new VariableNumber(1, 0),
                new VariableNumber(5, 0),
                getOtherType(oDistribution.getType()),
                new VariableNumber(3, 1)
        );
        t.setDistribution(tDistribution);
        t.setDecimalPlaces(0);

        return study;
    }

    private static double getRandomType() {
        int[] selection = new int[]{-1, 0, 1};
        return selection[random.nextInt(3)];
    }

    private static double getOtherType(double type) {
        if (type == 0) {
            int[] selection = new int[]{-1, 1};
            return selection[random.nextInt(2)];
        }
        return 0;
    }

}

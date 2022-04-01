package de.holube.fakestudy.old;

public class Study {
    public static final int AMOUNT_SUBJECTS = 40;
    private Subject[] subjects;
    private Category[] categories = new Category[5];

    public Study() {
        subjects = new Subject[AMOUNT_SUBJECTS];
        int tmpDistribution;

        // A:
        categories[0] = new Category(
                "Name"
        );

        // B:
        categories[1] = new Category(
                "Geschlecht",
                new String[]{"m", "w"},
                (AMOUNT_SUBJECTS / 2) - 5,
                (AMOUNT_SUBJECTS / 2) + 5
        );

        // C:
        categories[2] = new Category(
                "Alter in Jahren",
                Distribution.normal(new int[]{18, 90}, 5, 20, 5),
                0
        );

        // D:
        if (categories[2].distribution.getType() != Distribution.NORMAL) {
            tmpDistribution = Distribution.NORMAL;
        } else {
            tmpDistribution = (int) (Math.random() * 2) + 1;
        }

        categories[3] = new Category(
                "Fitness",
                Distribution.byType(tmpDistribution, new int[]{0, 100}, 5.0, 25.0, 2.5),
                1
        );

        // E:
        categories[4] = new Category(
                "Ausgangsgewicht",
                categories[3],
                new int[]{50, 150},
                Math.random() * 2,
                0
        );
    }

    public void convert() {
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = new Subject(categories.length);

            for (int j = 0; j < categories.length; j++) {
                subjects[i].set(j, categories[j].values[i]);
            }
        }
    }

    public Subject getSubject(int index) {
        return subjects[index];
    }

    public Category getCategory(int index) {
        return categories[index];
    }

    public int numOfCategories() {
        return categories.length;
    }
}

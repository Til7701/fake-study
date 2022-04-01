package de.holube.fakestudy;

public abstract class Category {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void calculate(int amountSubjects);

    public abstract String[] getStringResults();

}

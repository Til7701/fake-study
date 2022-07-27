package de.holube.fakestudy.category;

public abstract class Category {

    private String name;

    protected double missingPercentage;

    public String getName() {
        return name;
    }

    public double getMissingPercentage() {
        return missingPercentage;
    }

    public void setMissingPercentage(double missingPercentage) {
        this.missingPercentage = missingPercentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void calculate(int amountSubjects);

    public abstract String[] getStringResults();

    public abstract void setMissing();

}

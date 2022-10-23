package de.holube.fakestudy.study.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Category {

    protected double missingPercentage;
    private String name;

    public abstract void calculate(int amountSubjects);

    public abstract void setMissing();

    public abstract String[] getStringResults();

}

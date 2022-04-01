package de.holube.fakestudy.old;

public class Subject {

    private String[] values;

    public Subject(int size) {
        values = new String[size];
    }

    public String get(int i) {
        return values[i];
    }

    public void set(int i, String value) {
        values[i] = value;
    }
}

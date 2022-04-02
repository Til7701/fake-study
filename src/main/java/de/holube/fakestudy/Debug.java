package de.holube.fakestudy;

public class Debug {

    public static void main(String[] args) {
        Correlator correlator = new InvertedCorrelator();
        System.out.println(correlator.correlate(100, 1000, 109.5, 70, 130));
    }

}

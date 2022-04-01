module de.holube.fakestudy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires commons.math3;
    requires org.apache.poi.ooxml;


    opens de.holube.fakestudy to javafx.fxml;
    exports de.holube.fakestudy;
    exports de.holube.fakestudy.old;
    opens de.holube.fakestudy.old to javafx.fxml;
}
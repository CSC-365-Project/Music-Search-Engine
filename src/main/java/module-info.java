module engine.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires se.michaelthelin.spotify;


    opens hellofx to javafx.fxml;
    exports hellofx;
}
module engine.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens hellofx to javafx.fxml;
    exports hellofx;
}
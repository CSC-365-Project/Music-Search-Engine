package hellofx;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Controller {

        @FXML
        private Label label;

        public void initialize() {
                Query.init();
                Query.updateUserPwd("xcvna@gmail.com", "sb");
                // String javaVersion = System.getProperty("java.version");
                // String javafxVersion = System.getProperty("javafx.version");

                // label.setText("Song Name: " + res.get(0).get(0) + "\n" +
                // "URL: " + res.get(0).get(1) + "\n" +
                // "Popularity: " + res.get(0).get(2) + "\n" +
                // "Duration: " + res.get(0).get(3) + "\n" +
                // "Publish Date: " + res.get(0).get(4) + "\n");

                // label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " +
                // javaVersion + ".");
        }

        public void login(ActionEvent event) {

        }
        // @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        // actiontarget.setText("Sign in button pressed");
        // }
}
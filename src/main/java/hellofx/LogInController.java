package hellofx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LogInController {

    @FXML
    private Text text_welcome;
    @FXML
    private Text text_signUp;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField email_in;
    @FXML
    private PasswordField pass_in;

    public void initialize() {

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!email_in.getText().trim().isEmpty() && !pass_in.getText().trim().isEmpty()) {
                        LoginUtil.changeScene(event, "home.fxml", "Home Page", email_in, pass_in);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoginUtil.changeScene(event, "signUp.fxml", "Sign Up", null, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package hellofx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class SignInController implements Initializable {

    @FXML
    private Label label_welcome;
    @FXML
    private Label label_signUp;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    // @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    // actiontarget.setText("Sign in button pressed");
    // }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub

            }

        });
        Query.init();
    }
}
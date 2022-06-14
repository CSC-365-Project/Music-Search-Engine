
package hellofx;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class SignInController implements Initializable {

    @FXML
    private TextField email_in;
    @FXML
    private TextField usrName_in;
    @FXML
    private TextField usrPass_in;
    @FXML
    private TextField usrPass_conf;
    @FXML
    private Button login_button;
    @FXML
    private Button signUp_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Query.init();
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoginUtil.changeScene(event, "login.fxml", "Login", null, null,null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        signUp_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoginUtil.signUpUser(event, email_in, usrName_in, usrPass_in, usrPass_conf, "login.fxml");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
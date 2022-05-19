
package hellofx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HomeController {
    @FXML
    private Text user_name;

    public void initData(TextField email) {
        Query.init();
        user_name.setText("Hello," + email.getText());
    }

    public void initialize() {
    }
}
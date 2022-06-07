
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
    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;

    private String email;

    public void initData(String email) {
        Query.init();
        user_name.setText("Hello," + email);
        this.email = email;
    }

    public void initialize() {
        searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    LoginUtil.changeScene(event, "search.fxml", "Search", email, null, searchText.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}

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
    @FXML
    private Button favoriteButton;
    @FXML
    private Button top100Button;
    @FXML
    private Button newButton;

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

        top100Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    System.out.println("In set up button");
                    LoginUtil.changeSceneTop100(event, "top100.fxml", "Top 100", email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
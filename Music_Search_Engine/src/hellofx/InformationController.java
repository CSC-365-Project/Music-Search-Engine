
package hellofx;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class InformationController {

    @FXML
    private Text songNameText;
    @FXML
    private Text artistNameText;
    @FXML
    private Text albumNameText;
    @FXML
    private Text genreNameText;
    @FXML
    private Text popularityNameText;
    @FXML
    private Text publishDateText;
    @FXML
    private Text artistDesText;
    @FXML
    private Button addFavButton;
    @FXML
    private Button playButton;
    @FXML
    private Button backButton;
    private String email;
    private String songID;

    public void initData(String email, String songID) {
        Query.init();
        this.email = email;
        this.songID = songID;
        List<String> infoList = Query.getSongDisplayInfo(songID);
        songNameText.setText(infoList.get(0));
        artistNameText.setText(infoList.get(1));
        albumNameText.setText(infoList.get(2));
        genreNameText.setText(infoList.get(3));
        popularityNameText.setText(infoList.get(4));
        publishDateText.setText(infoList.get(5));
        artistDesText.setText(infoList.get(6));

    }

    public void initialize() {
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    Query.init();
                    String passworrd = Query.getPassword(email);
                    LoginUtil.changeScene(event, "home.fxml", "Home", email, passworrd, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

        addFavButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                Query.init();
                Query.addToFavList(songID, email);
            }

        });
    }
}
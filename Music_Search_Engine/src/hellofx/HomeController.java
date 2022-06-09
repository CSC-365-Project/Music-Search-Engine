
package hellofx;

import java.io.IOException;
import java.util.List;
import java.awt.Desktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Button top10Button;
    @FXML
    private Button newlyReleasedButton;
    @FXML
    private Button playSongButtom;

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
                    LoginUtil.changeSceneSearch(event, "search.fxml", "Search", email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        top10Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    System.out.println("In set up button");
                    LoginUtil.changeSceneTop10(event, "top10.fxml", "Top 10", email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        newlyReleasedButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    LoginUtil.changeSceneNewlyReleased(event, "newlyReleased.fxml", "What's New", email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        favoriteButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    LoginUtil.changeSceneFavorite(event, "favorite.fxml", "Favorite", email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        // playSongButtom.setOnAction(new EventHandler<ActionEvent>() {

        // @Override
        // public void handle(ActionEvent event) {
        // // TODO Auto-generated method stub
        // ObservableList<Album> displayList = FXCollections.observableArrayList();
        // Query.init();
        // List<String> listOfID = Query.searchByName(searchText.getText().trim());
        // String songID = Query.
        // Query.init();
        // String url = Query.getURL(songID);

        // }
        // });

        // // public static void playMedia(String url){
        // // Media media = new Media(url);
        // // MediaPlayer player = new MediaPlayer(media);
        // // player.play();
        // // }
        // // public static void openWebpage(String url) {
        // // try {
        // // Desktop.getDesktop().browse(new URL(url).toURI());
        // // } catch (IOException | URISyntaxException e) {
        // // e.printStackTrace();
        // // }
        // // }
    }
}
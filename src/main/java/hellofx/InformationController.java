
package hellofx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.awt.Desktop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    // @FXML
    // private Text artistDesText;
    @FXML
    private Button addFavButton;
    @FXML
    private Button playButton;
    @FXML
    private Button backButton;
    @FXML
    private Button artistInfoButton;

    private String email;
    private String songID;

    private String url;

    private Media media;
    private MediaPlayer player;

    boolean play = false;

    public void init(String url){
        if (!url.contains("open")) {
            media = new Media(url);
            player = new MediaPlayer(media);
            this.url = url;
        }else {
            this.url = url;
        }
    }

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
        publishDateText.setText(infoList.get(6));

    }

    public void initialize() {
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    Query.init();
                    String password = Query.getPassword(email);
                    LoginUtil.changeScene(event, "home.fxml", "Home", email, password, null);
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
                System.out.println(songID + email);
                Query.addToFavList(songID, email);
            }

        });

        artistInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Query.init();
                List<String> infoList = Query.getSongDisplayInfo(songID);
                String url = infoList.get(7);
                                    
                try {
                    Desktop.getDesktop().browse(new URL(url).toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                Query.init();
                if (url.contains("open")) {
                    try {
                        Desktop.getDesktop().browse(new URL(url).toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!play) {
                        player.play();
                        play = true;
                    }else{
                        player.stop();
                        play = false;
                    }
                }
            }

        });
    }
}
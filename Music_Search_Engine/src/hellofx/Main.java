package hellofx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

    static Connection connect;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Music Search Engine");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Media media = new Media("https://p.scdn.co/mp3-preview/0b61b7c36bd426086481769a4bb2e08cc417a01b?cid=75b3100d240d48928aac802662420b21");
        MediaPlayer player = new MediaPlayer(media); 
        player.play();
    }

    public static void main(String[] args) throws Exception {

        Query.init();

        
        launch(args);
    }
}
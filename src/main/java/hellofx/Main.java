package hellofx;

import java.sql.*;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

    static Connection connect;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Music Search Engine");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
//        Media media = new Media("https://p.scdn.co/mp3-preview/b362f060e5b9261aca193c58e694b1f097d2cd41?cid=75b3100d240d48928aac802662420b21");; //replace /Movies/test.mp3 with your file
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();

    }

    public static void main(String[] args) throws Exception {

        Query.init();
        // Query.getAllSong();
        // List<List<String>> res = new ArrayList<>();
        // res = Query.getRecentPopularSong();
        // for(int i = 0; i<res.size(); i++){
        //     System.out.print(res.get(i));
        // }
        launch(args);
    }
}
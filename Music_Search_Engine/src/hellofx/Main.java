package hellofx;

import java.sql.*;

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
    }

    public static void main(String[] args) throws Exception {

        Query.init();
        // String url = Query.getURL("000u1dTg7y1XCDXi80hbBX");
        // System.out.print(url);
        launch(args);
    }
}
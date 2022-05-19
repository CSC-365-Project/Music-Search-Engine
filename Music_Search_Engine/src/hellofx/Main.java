package hellofx;

import java.sql.*;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        List<List<String>> res = Query.getFavList("xcvna@gmail.com");

        System.out.println("Song Name: " + res.get(0).get(0) + "\n" +
        "URL: " + res.get(0).get(1) + "\n" +
        "Popularity: " + res.get(0).get(2) + "\n" +
        "Duration: " + res.get(0).get(3) + "\n" +
        "Publish Date: " + res.get(0).get(4) + "\n");

        launch(args);
    }
}
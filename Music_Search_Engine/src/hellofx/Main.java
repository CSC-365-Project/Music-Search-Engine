package hellofx;

import java.sql.*;
import java.util.ArrayList;

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
        // Query.getAllSong();
        List<List<String>> res = new ArrayList<>();
        res = Query.getNewReleasedSong();
        for(int i = 0; i<res.size(); i++){
            System.out.printf(res.get(i))
        }

        launch(args);
    }
}
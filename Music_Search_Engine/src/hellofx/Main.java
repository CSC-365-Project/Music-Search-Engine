package hellofx;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Connection connect;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        primaryStage.setTitle("Music Search Engine");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static Connection connectdb(String hostname, String userName, String password) {
        // try to connect to db with given hostname, username, and password
        // username and password might need further encode

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/" + hostname + "?" + "user=" + userName + "&password=" + password);
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;  
    }


    public static void main(String[] args) throws Exception {

        // Getting connection from shared db.
        Connection connect = connectdb("musicsearchengine", "musicsearchengine", "88888888");

        // sample query
        String selectAllUsers = "select * from TestUsers;";
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(selectAllUsers);

        //sample printing
        while (rs.next()){
            String userName= rs.getString(2);
            System.out.println("Test User Name: " + userName);
        }
        
        launch(args);
    }
}
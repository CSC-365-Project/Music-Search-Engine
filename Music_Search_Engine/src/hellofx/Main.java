package hellofx;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> readinfo(String file){
        List<String> info = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    
            String strLine;
    
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)
                info.add(strLine);   
            //Close the input stream
            fstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public static void main(String[] args) throws Exception {

        List<String> info = readinfo("Music_Search_Engine/src/hellofx/.info");

        // Getting connection from shared db.
        Connection connect = Connector.connectdb(info.get(0), info.get(1), info.get(2));

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
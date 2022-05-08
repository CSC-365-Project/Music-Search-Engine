package hellofx;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {

    static Connection connect;

    public static void init(){

        List<String> info = readinfo("Music_Search_Engine/src/hellofx/.info");

        // Getting connection from shared db.
        connect = Connector.connectdb(info.get(0), info.get(1), info.get(2));
    }

    public static void selectAllUsers(){
        // sample query
        String selectAllUsers = "select * from TestUsers;";
        Statement statement;
        try {
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(selectAllUsers);

            //sample printing
            while (rs.next()){
                String userName= rs.getString(2);
                System.out.println("Test User Name: " + userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
}

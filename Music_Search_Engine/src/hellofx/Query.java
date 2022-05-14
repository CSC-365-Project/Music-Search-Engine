package hellofx;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Insets;

public class Query {

    static Connection connect;

    public static void init(){

        List<String> info = readinfo("Music_Search_Engine/src/hellofx/.info");

        // Getting connection from shared db.
        connect = Connector.connectdb(info.get(0), info.get(1), info.get(2));
    }

    public static List<List<String>> getAllSong(){
        // sample query
        List<List<String>> res = new ArrayList<>();

        String selectSQL = "select * from Songs;";
        Statement statement;
        try {
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);

            int id = 0;

            //sample printing
            while (rs.next()){
                String songName= rs.getString("songName");
                String url= rs.getString("url");
                String popularity= rs.getString("popularity");
                String duration= rs.getString("duration");
                String date= rs.getString("publishDate");
                
                List<String> info = new ArrayList<>();
                info.add(songName);
                info.add(url);
                info.add(popularity);
                info.add(duration);
                info.add(date);
                res.add(info);

                id++;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
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

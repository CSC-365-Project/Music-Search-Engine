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

    // Sample query to return all song in the Song table
    public static List<List<String>> getAllSong(){
        // sample query
        List<List<String>> res = new ArrayList<>();

        String sql = "select * from Songs;";
        Statement statement;
        try {
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

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
            }

            statement.close();
            connect.commit();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getPassword(String email){

        String password = "";
        String sql = "select U.pwd from Users U where U.email = ?";
        
        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                password = rs.getString("pwd");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void addNewUser(String email, String name, String password) {
        String sql = "insert into Users(email, name, pwd) Values (?, ?, ?);";

        PreparedStatement statement;
        
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);

            statement.executeUpdate();

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> removeUser(String email){

        List<String> res = new ArrayList<>();

        String sql = "delete from Users where email = ?; ";
        String res_sql = "select * from Users where email = ?;";

        PreparedStatement statement;
        
        try {
            connect.setAutoCommit(false);
            statement = connect.prepareStatement(res_sql);
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            rs.next();
            res.add(rs.getString("email"));
            res.add(rs.getString("name"));
            res.add(rs.getString("pwd"));

            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();

            statement.close();
            connect.commit();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
    
    public static void updateUserName(String email, String name){
        String sql = "update Users set name = ? where email = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
            
            statement.close();
            connect.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUserPwd(String email, String pwd){
        String sql = "update Users set pwd = ? where email = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, pwd);
            statement.setString(2, email);
            statement.executeUpdate();
            
            statement.close();
            connect.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<List<String>> getFavList(String email){

        List<List<String>> res = new ArrayList<>();

        String sql = "select * from Songs, (select F.songID from Favorite F where F.userEmail = ?) as FavSongs where Songs.songID = FavSongs.songID";
        
        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

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
            }

            statement.close();
            connect.close();

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

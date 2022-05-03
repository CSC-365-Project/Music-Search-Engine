import java.sql.*;

public class App {

    static Connection connect;

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

    }

    public static Connection connectdb(String hostname, String userName, String password){
        // try to connect to db with given hostname, username, and password
        // username and password might need further encode

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/" + hostname + "?" + "user=" + userName + "&password=" + password);
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;  
    }
}

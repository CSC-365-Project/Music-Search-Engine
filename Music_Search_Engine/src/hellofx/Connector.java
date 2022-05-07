package hellofx;

import java.sql.*;

public class Connector {

    static Connection connect;

    public static Connection connectdb(String hostname, String userName, String password) {
        // try to connect to db with given hostname, username, and password
        // username and password might need further encode

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/" + hostname + "?" + "user=" + userName + "&password=" + password);
                 
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load JDBC driver");
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
        catch (SQLException ex){
            System.err.println("SQLException information");
            while(ex!=null){
                System.err.println("Error msg: " + ex.getMessage());
                System.err.println("SQL state: " + ex.getSQLState());
                System.err.println("Error code: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return connect;  
    }
    
}

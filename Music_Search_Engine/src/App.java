import java.sql.*;

public class App {

    static Connection connect;

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/musicsearchengine?user=musicsearchengine&password=88888888");

            String selectAllUsers = "select * from TestUsers;";
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(selectAllUsers);
            while (rs.next()){
                String userName= rs.getString(2);
                System.out.println("Test User Name: " + userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

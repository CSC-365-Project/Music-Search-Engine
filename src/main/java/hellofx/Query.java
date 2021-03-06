package hellofx;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Insets;

public class Query {

    static Connection connect;

    public static void init() {

        List<String> info = readinfo("src/main/java/hellofx/.info");

        // Getting connection from shared db.
        connect = Connector.connectdb(info.get(0), info.get(1), info.get(2));
    }

    public static void close() throws SQLException {
        connect.close();
    }

    // // Sample query to return all song in the Song table
    // public static List<List<String>> getAllSong() {
    // // sample query
    // List<List<String>> res = new ArrayList<>();

    // String sql = "select * from Songs;";
    // Statement statement;
    // try {
    // statement = connect.createStatement();
    // ResultSet rs = statement.executeQuery(sql);

    // // sample printing
    // while (rs.next()) {
    // String songName = rs.getString("songName");
    // String url = rs.getString("url");
    // String popularity = rs.getString("popularity");
    // String duration = rs.getString("duration");
    // String date = rs.getString("publishDate");

    // List<String> info = new ArrayList<>();

    // info.add(songName);
    // info.add(url);
    // info.add(popularity);
    // info.add(duration);
    // info.add(date);
    // res.add(info);
    // }

    // statement.close();
    // connect.commit();
    // connect.close();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return res;
    // }

    // Sample query to return all song in the Song table
    public static List<List<String>> getAllSong() {
        // sample query
        List<List<String>> res = new ArrayList<>();

        String sql = "select * from Songs;";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            // sample printing
            while (rs.next()) {
                String songID = rs.getString("songID");
                String songName = rs.getString("songName");
                String url = rs.getString("url");
                String popularity = rs.getString("popularity");
                String duration = rs.getString("duration");
                String date = rs.getString("publishDate");

                List<String> info = new ArrayList<>();
                info.add(songID);
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

    public static String getPassword(String email) {
        String password = "";
        String sql = "select U.pwd from Users U where U.email = ?";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                password = rs.getString("pwd");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void addNewUser(String email, String name, String password) {
        String sql = "insert into Users(email, name, pwd) Values (?, ?, ?)";

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

    public static List<String> removeUser(String email) {

        List<String> res = new ArrayList<>();

        String sql = "delete from Users where email = ?";
        String res_sql = "select * from Users where email = ?";

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

    public static void updateUserName(String email, String name) {
        String sql = "update Users set name = ? where email = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();

            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserPwd(String email, String pwd) {
        String sql = "update Users set pwd = ? where email = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, pwd);
            statement.setString(2, email);
            statement.executeUpdate();

            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> readinfo(String file) {
        List<String> info = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            // Read File Line By Line
            while ((strLine = br.readLine()) != null)
                info.add(strLine);
            // Close the input stream
            fstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public static String findSongNamebyID(String songID) {
        String songName = "";
        String sql = "select S.songName from Songs S where S.songID = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songID);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                songName = rs.getString("songName");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songName;
    }

    public static String findArtistNamebyID(String songID) {
        String artistName = "";
        String sql = "select A.artistName from Artists A, Songs S where S.artistID = A.artistID and S.songID = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songID);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                artistName = rs.getString("artistName");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistName;
    }

    public static String findGenrebyID(String songID) {
        String genre = "";
        String sql = "select genreName from Songs where songID = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songID);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                genre = rs.getString("genreName");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public static List<String> getFavoriteSongs(String email) {
        List<String> songNames = new ArrayList<String>();
        String sql = "select S.songName from Songs S where S.songID in (select F.songID from Favorite F where F.userEmail = ?)";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("songName");
                songNames.add(name);
            }

            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songNames;
    }

    // return a list of song IDs with the same name
    public static List<String> searchByName(String songName) {
        List<String> songIDs = new ArrayList<String>();
        String sql = "select S.songID from Songs S where S.songName = ? limit 10";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String songID = rs.getString("songID");
                songIDs.add(songID);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songIDs;
    }

    public static List<String> searchByArtist(String artistName) {
        List<String> songIDs = new ArrayList<String>();
        String sql = "select S.songID from Songs S, Artists A where S.artistID = A.artistID and A.artistName = ? limit 10";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, artistName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String songID = rs.getString("songID");
                songIDs.add(songID);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songIDs;
    }

    public static List<String> searchByGenre(String genreName) {
        List<String> songIDs = new ArrayList<String>();
        String sql = "select songID from Songs where genreName = ? limit 10";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, genreName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String songID = rs.getString("songID");
                songIDs.add(songID);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songIDs;
    }

    public static void createFavoriteList(String plName, String userEmail) {
        String sql = "insert into PlaylistOwnership(playlistName, userEmail) values (?, ?)";

        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, plName);
            statement.setString(2, userEmail);

            statement.executeUpdate();

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.print("insert success!");
    }

    public static void addToPlayList(String songName, int playlistID) {
        String sql1 = "select P.playlistID from PlaylistOwnership P where P.playlistName = ?)";
        String sql2 = "insert into PlaylistSongs(playlistID, songID) values (?, ?)";
        int songID = 0;

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql1);
            statement.setString(1, songName);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                songID = rs.getInt("songID");

            statement = connect.prepareStatement(sql2);
            statement.setInt(1, playlistID);
            statement.setInt(2, songID);

            statement.executeQuery();

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToFavList(String songID, String userEmail) {
        String sql = "insert into Favorite(userEmail, songID)values(?,?)";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, userEmail);
            statement.setString(2, songID);

            statement.executeUpdate();

            statement.close();
            connect.close();
            System.out.print("insert success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSongInAlbums(String albumName) {
        List<String> songNames = new ArrayList<String>();
        String sql = "select S.songName from Songs S, Albums A where S.albumID = A.albumID and A.albumName = ?";
        String song = "";

        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, albumName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                song = rs.getString("songName");
                songNames.add(song);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songNames;
    }

    public static String getArtistNameBySong(String songName) {
        String sql = "select A.artistName from Artists A, Songs S where A.artistID = S.artistID and S.songName = ?";
        String artistName = "";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songName);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
                artistName = rs.getString("artistName");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistName;
    }

    public static List<String> getAlbumNameByArtists(String artistName) {
        String sql = "select A.albumName from Albums A where A.albumID = (select distinct S.albumID from Songs S, Artists A where S.artistID = A.artistID and A.artistName = ?)";
        List<String> albumNames = new ArrayList<String>();
        String albumName = "";

        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, artistName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                albumName = rs.getString("albumName");
                albumNames.add(albumName);
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumNames;
    }

    public static List<String> getSongInfo(String songName) {

        List<String> info = new ArrayList<>();

        String sql = "SELECT A.artistName,S.publishDate,B.albumName,G.name,S.duration FROM Songs S, Artists A, Albums B, Genres G WHERE(S.songName = ? AND S.albumID = B.albumID AND S.genreID = G.genreId AND S.artistID = A.artistID)";

        PreparedStatement statement;
        try {

            statement = connect.prepareStatement(sql);
            statement.setString(1, songName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String artistName = rs.getString("artistName");
                String publishDate = rs.getString("publishDate");
                String albumName = rs.getString("albumName");
                String name = rs.getString("name");
                String duration = rs.getString("duration");

                info.add(artistName + "\n");
                info.add(publishDate + "\n");
                info.add(albumName + "\n");
                info.add(name + "\n");
                info.add(duration + "\n");
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    // the data printed is not well formated, but works
    // need to update the time when running
    public static List<List<String>> getNewReleasedSong() {
        // sample query
        List<List<String>> res = new ArrayList<>();

        String sql = "SELECT * FROM Songs WHERE publishDate BETWEEN '2022-6-1' AND '2022-6-8' limit 10";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            // sample printing
            while (rs.next()) {
                String songID = rs.getString("songID");
                String songName = rs.getString("songName");
                String url = rs.getString("url");
                String popularity = rs.getString("popularity");
                String duration = rs.getString("duration");
                String date = rs.getString("publishDate");

                List<String> info = new ArrayList<>();
                info.add(songID);
                info.add(songName);
                info.add(url);
                info.add(popularity);
                info.add(duration);
                info.add(date + "\n");
                res.add(info);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // the data printed is not well formated, but works
    // need to update the time and number of songs(LIMIT) when running
    public static List<List<String>> getRecentPopularSong() {
        // sample query
        List<List<String>> res = new ArrayList<>();

        String sql = "SELECT * FROM Songs S WHERE publishDate BETWEEN '2021-5-9' AND '2022-6-9' ORDER BY S.popularity DESC LIMIT 10";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            // sample printing
            while (rs.next()) {
                String songID = rs.getString("songID");
                String songName = rs.getString("songName");
                String url = rs.getString("url");
                String popularity = rs.getString("popularity");
                String duration = rs.getString("duration");
                String date = rs.getString("publishDate");

                List<String> info = new ArrayList<>();
                info.add(songID);
                info.add(songName);
                info.add(url);
                info.add(popularity);
                info.add(duration);
                info.add(date + "\n");
                res.add(info);
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getURL(String songID) {
        String url = "";
        String sql = "SELECT S.url FROM Songs S WHERE S.songID = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songID);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                url = rs.getString("url");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getUserName(String userEmail) {
        String url = "";
        String sql = "SELECT name FROM Users WHERE email = ?";

        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, userEmail);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                url = rs.getString("name");

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static int URLCheck(String url) {
        if (url.charAt(8) == 'o') {
            return 0;
        } else {
            return 1;
        }
    }

    public static List<String> getSongDisplayInfo(String songID) {
        // sample query
        List<String> res = new ArrayList<>();

        String sql = "SELECT\n" +
                "    S.songName,\n" +
                "    A.artistName,\n" +
                "    A2.albumName,\n" +
                "    S.genreName,\n" +
                "    S.popularity,\n" +
                "    S.url,\n" +
                "    S.publishDate,\n" +
                "    A.description\n" +
                "FROM (select * from Songs where songID = ?) as S\n" +
                "         JOIN Artists A on S.artistID = A.artistID\n" +
                "         JOIN Albums A2 on S.albumID = A2.albumID;";
        PreparedStatement statement;
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, songID);
            ResultSet rs = statement.executeQuery();
            // sample printing
            while (rs.next()) {
                String songName = rs.getString("songName");
                String artistName = rs.getString("artistName");
                String albumName = rs.getString("albumName");
                String genreName = rs.getString("genreName");
                String popularity = rs.getString("popularity");
                String url = rs.getString("url");
                String publishDate = rs.getString("publishDate");
                String description = rs.getString("description");

                res.add(songName);
                res.add(artistName);
                res.add(albumName);
                res.add(genreName);
                res.add(popularity);
                res.add(url);
                res.add(publishDate);
                res.add(description);
            }
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void insertSong(List<String> songInfo) {
        String sql = "insert into Songs(songID, songName, url, popularity, duration, publishDate, albumID, artistID, genreName) "
                +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, songInfo.get(0));
            stmt.setString(2, songInfo.get(1));
            stmt.setString(3, songInfo.get(2));
            stmt.setInt(4, Integer.parseInt(songInfo.get(3)));
            stmt.setInt(5, Integer.parseInt(songInfo.get(4)));
            stmt.setDate(6, Date.valueOf(songInfo.get(5)));
            stmt.setString(7, songInfo.get(6));
            stmt.setString(8, songInfo.get(7));
            stmt.setString(9, songInfo.get(8));
            stmt.executeUpdate();
            System.out.println("Records inserted");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void insertArtist(List<String> Info) {
        String sql = "insert into Artists(artistID, artistName, follower, description) values(?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, Info.get(0));
            stmt.setString(2, Info.get(1));
            stmt.setInt(3, Integer.parseInt(Info.get(2)));
            stmt.setString(4, Info.get(3));
            stmt.executeUpdate();
            System.out.println("Artist inserted");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void insertAlbum(List<String> Info) {
        String sql = "insert into Albums(albumID, albumName, artistID, publishDate) " +
                "values(?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, Info.get(0));
            stmt.setString(2, Info.get(1));
            stmt.setString(3, Info.get(2));
            stmt.setDate(4, Date.valueOf(Info.get(3)));
            stmt.executeUpdate();
            System.out.println("Album inserted");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static List<String> getSongIDByEmail(String userEmail) {
        String sql = "select songID from Favorite where userEmail = ?";
        List<String> songIDs = new ArrayList<String>();
        String songID = "";

        PreparedStatement statement;

        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, userEmail);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                songID = rs.getString("songID");
                songIDs.add(songID);
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songIDs;
    }

}

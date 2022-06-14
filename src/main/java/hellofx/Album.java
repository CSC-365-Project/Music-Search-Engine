package hellofx;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class Album {
    private String songName;
    private String artistName;
    private String genre;
    private String songID;
    private String url;

    public Album(String songName, String artistName, String genre, String songID, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.songID = songID;
        this.url = url;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getSongID() {
        return this.songID;
    }

}

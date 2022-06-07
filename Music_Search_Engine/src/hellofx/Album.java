package hellofx;

public class Album {
    private String songName;
    private String artistName;
    private String genre;
    private String songID;

    public Album(String songName, String artistName, String genre, String songID) {
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.songID = songID;
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

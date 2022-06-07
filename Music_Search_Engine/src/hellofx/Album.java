package hellofx;

public class Album {
    private String songName;
    private String artistName;
    private String genre;

    public Album(String songName, String artistName, String genre) {
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
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

}

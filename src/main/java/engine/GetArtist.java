package engine;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Arrays;


public class GetArtist {
    private static final String accessToken = Authorization.getAccessToken();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static String getArtistNameById(String ArtistId) {
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(ArtistId)
                    .build();
            Artist artist = getArtistRequest.execute();

            return artist.getName();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static String getArtistGenresById(String ArtistId) {
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(ArtistId)
                    .build();
            Artist artist = getArtistRequest.execute();
            if (artist.getGenres().length != 0){
                return artist.getGenres()[0];
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static String getArtistFollowersById(String ArtistId) {
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(ArtistId)
                    .build();
            Artist artist = getArtistRequest.execute();

            return artist.getFollowers().getTotal().toString();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static String getArtistTypeById(String ArtistId) {
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(ArtistId)
                    .build();
            Artist artist = getArtistRequest.execute();

            return artist.getType().toString();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static String getArtistExternalUrl(String ArtistId){
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(ArtistId)
                    .build();
            Artist artist = getArtistRequest.execute();

            return "https://open.spotify.com/artist/" + artist.getId();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }
}

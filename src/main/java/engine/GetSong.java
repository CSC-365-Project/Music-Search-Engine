package engine;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.requests.data.episodes.GetEpisodeRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetSong {
    private static final String accessToken = Authorization.getAccessToken();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static void getEpisode_Sync(String id) {
        try {
            GetEpisodeRequest getEpisodeRequest = spotifyApi.getEpisode(id)
//          .market(CountryCode.SE)
                    .build();
            final Episode episode = getEpisodeRequest.execute();

            System.out.println("Name: " + episode.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

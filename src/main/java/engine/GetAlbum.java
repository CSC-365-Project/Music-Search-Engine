package engine;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Arrays;

public class GetAlbum {
    private static final String accessToken = Authorization.getAccessToken();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static void getAlbum_Sync(String id) {
        try {
            GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id)
//          .market(CountryCode.SE)
                    .build();
            final Album album = getAlbumRequest.execute();

            System.out.println("Name: " + album.getTracks());
            System.out.println("Genres: " + Arrays.toString(album.getGenres()));
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //                String AlbumInfo = Arrays.toString(((Track) playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getArtists());
//                String AlbumName = AlbumInfo.substring(AlbumInfo.indexOf("=")+1,
//                        AlbumInfo.indexOf(",", AlbumInfo.indexOf("=")+1));
//                String AlbumId = AlbumInfo.substring(AlbumInfo.indexOf("id=")+1,
//                        AlbumInfo.indexOf(",", AlbumInfo.indexOf("id=")+1));
//                System.out.println("Album's name: " + AlbumName);
//                System.out.println("Album's id: " + AlbumInfo);
}
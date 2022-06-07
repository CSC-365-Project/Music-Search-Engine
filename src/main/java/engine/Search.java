package engine;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Search {
    private static final String accessToken = Authorization.getAccessToken();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static String searchAlbumsIdByName(String q) {
        try {
            SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(q)
//          .market(CountryCode.SE)
          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                    .build();
            Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();

            return albumSimplifiedPaging.getItems()[0].getId();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static String searchArtistsIdByName(String q) {
        try {
            SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
//          .market(CountryCode.SE)
          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                    .build();
            Paging<Artist> artistPaging = searchArtistsRequest.execute();

            return artistPaging.getItems()[0].getId();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static void searchTrackIdByName(String q) {
        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
//          .market(CountryCode.SE)
            .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                    .build();

            Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println(trackPaging.getItems()[0].getName());
            System.out.println(trackPaging.getItems()[0].getId());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    public static void searchItem(String q, String type) {
//        try {
//            SearchItemRequest searchItemRequest = spotifyApi.searchItem(q, type)
////          .market(CountryCode.SE)
////          .limit(10)
////          .offset(0)
////          .includeExternal("audio")
//                    .build();
//
//            SearchResult searchResult = searchItemRequest.execute();
//            System.out.println("Total tracks: " + searchResult.getTracks());
//        } catch (IOException | SpotifyWebApiException | ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

//
//    public static void main(String[] args) {
//        System.out.println(searchArtistsIdByName("ColdPlay"));
//    }
}

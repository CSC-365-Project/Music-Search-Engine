package engine;

import engine.Authorization;
import engine.GetArtist;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;

public class Spotify {

    private static final String accessToken = Authorization.getAccessToken();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

//    public static void getPlaylist_Sync() {
//        try {
//            final Playlist playlist = getPlaylistRequest.execute();
//
//            System.out.println(playlist);
//            System.out.println("Name: " + playlist.getName());
//            System.out.println(playlist.getFollowers().getTotal());
//            System.out.println(playlist.getTracks().getTotal());
//            System.out.println(playlist.getDescription());
//        } catch (IOException | SpotifyWebApiException | ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }


    public static void getPlaylistsItems_Sync(String playlistId) {
        try {
            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
                    .getPlaylistsItems(playlistId)
//          .fields("description")
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
                    .build();
            final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();

            for (int i = 0; i < playlistTrackPaging.getTotal(); i++) {
                System.out.println(i+1 + ": ");
                String songId = playlistTrackPaging.getItems()[i].getTrack().getId();
                String songName = playlistTrackPaging.getItems()[i].getTrack().getName();
                String url = "https://open.spotify.com/track/" + (playlistTrackPaging.getItems()[i].getTrack()).getId();
                String popularity = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getPopularity());
                String durationMS = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getDurationMs());
                String publishDate = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDate();
                if (((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDatePrecision().toString().equals("YEAR")){
                    publishDate = publishDate + "-01-01";
                }
                String artistName = ((Track) playlistTrackPaging.getItems()[i].getTrack()).getArtists()[0].getName();
                String artistId = ((Track) playlistTrackPaging.getItems()[i].getTrack()).getArtists()[0].getId();

                String albumName = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getName();
                String albumId = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getId();

                System.out.println("song's id: " + songId);
                System.out.println("song's name: " + songName);
                System.out.println("url: " + url);
                System.out.println("Popularity :" + popularity);
                System.out.println("Duration :" + durationMS);
                System.out.println("Publish Date :" + publishDate);

                System.out.println("Track's artist name: " + artistName);
                System.out.println("Track's artist id: " + artistId);

                System.out.println("Album's name: " + albumName);
                System.out.println("Album's id: " + albumId);

                String genres = GetArtist.getArtistGenresById(artistId);
                System.out.println("genres = " + genres);
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getPlaylistsItems_Sync("37i9dQZEVXbMDoHDwVN2tF");
    }
}
package engine;

import engine.Authorization;
import engine.GetArtist;
import hellofx.Query;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Query.init();
            for (int i = 2; i < 7; i++) {
                String songId = playlistTrackPaging.getItems()[i].getTrack().getId();
                String songName = playlistTrackPaging.getItems()[i].getTrack().getName();
                String url = "https://open.spotify.com/track/" + (playlistTrackPaging.getItems()[i].getTrack()).getId();
                String popularity = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getPopularity());
                String durationMS = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getDurationMs());
                String publishDate = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDate();
                if (((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDatePrecision().toString().equals("YEAR")){
                    publishDate = publishDate + "-01-01";
                }

                String artistId = ((Track) playlistTrackPaging.getItems()[i].getTrack()).getArtists()[0].getId();

                String albumName = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getName();
                String albumId = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getId();

                String artistName = GetArtist.getArtistNameById(artistId);
                String genres = GetArtist.getArtistGenresById(artistId);
                String followers = GetArtist.getArtistFollowersById(artistId);
                String type = GetArtist.getArtistTypeById(artistId);

                List<String> songInfo = Arrays.asList(songId, songName, url, popularity, durationMS, publishDate, artistId, albumId, genres);
                Query.insertSong(songInfo);
            }
            Query.close();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        getPlaylistsItems_Sync("37i9dQZEVXbMDoHDwVN2tF");
    }
}
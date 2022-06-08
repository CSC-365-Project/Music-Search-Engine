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


    public static void insertPlaylisttoDB(String playlistId, int count) {
        try {
            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
                    .getPlaylistsItems(playlistId)
//          .fields("description")
//          .limit(10)
            .offset(count)
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
                    .build();

            final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();
            Query.init();
            int n = playlistTrackPaging.getTotal();
            if (n > 100){
                n = 100;
            }

            for (int i = 0; i < n; i++) {
                System.out.println("Song: " + (count+i+1));
                String songId = playlistTrackPaging.getItems()[i].getTrack().getId();
                String songName = playlistTrackPaging.getItems()[i].getTrack().getName();
                if(songName.length() > 50){
                    songName = songName.substring(0,50);
                }
                String TrackInfo = String.valueOf(playlistTrackPaging.getItems()[i].getTrack());
                String previewUrl = TrackInfo.substring(TrackInfo.indexOf("previewUrl=")+11, TrackInfo.indexOf(", trackNumber="));
                if (previewUrl.length() == 4){
                    previewUrl = "https://open.spotify.com/track/" + songId;
                }
                String popularity = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getPopularity());
                String durationMS = String.valueOf(((Track)playlistTrackPaging.getItems()[i].getTrack()).getDurationMs());
                String publishDate = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDate();
                if (((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getReleaseDatePrecision().toString().equals("YEAR")){
                    publishDate = publishDate + "-01-01";
                }

                String artistId = ((Track) playlistTrackPaging.getItems()[i].getTrack()).getArtists()[0].getId();
                String genres = GetArtist.getArtistGenresById(artistId);

                String artistName = GetArtist.getArtistNameById(artistId);
                String followers = GetArtist.getArtistFollowersById(artistId);
                String artistUrl = GetArtist.getArtistExternalUrl(artistId);

                String albumId = ((Track)playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getId();
                String albumName = GetAlbum.getAlbumName(albumId);

                List<String> songInfo = Arrays.asList(songId, songName, previewUrl, popularity, durationMS, publishDate, albumId, artistId, genres);
                List<String> artistInfo = Arrays.asList(artistId, artistName, followers, artistUrl);
                List<String> albumInfo = Arrays.asList(albumId, albumName, artistId, publishDate);

                try{
                    Query.insertSong(songInfo);
                }catch(Exception ignored){
                }
                try {
                    Query.insertArtist(artistInfo);
                }catch(Exception ignored){
                }
                try {
                    Query.insertAlbum(albumInfo);
                }catch(Exception ignored){
                }
            }
            Query.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        for (int i=0 ; i < 14; i++) {
            insertPlaylisttoDB("1ejRUZcod8DSFqsW5WIkmm", i*100);
        }
//        for (int i=0 ; i < 15; i++) {
//            insertPlaylisttoDB("6FMls6rmInujNCikuWcuEk", i*100);
//        }
    }
}
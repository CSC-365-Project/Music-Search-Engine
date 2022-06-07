package hellofx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SearchController {
    @FXML
    private TableView<Album> songTable;
    @FXML
    private TableColumn<Album, String> songNameColumn;
    @FXML
    private TableColumn<Album, String> artistColumn;
    @FXML
    private TableColumn<Album, String> genreColumn;
    @FXML
    private Button backButton;
    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;
    @FXML
    private Button byName;
    @FXML
    private Button byArtist;
    @FXML
    private Button byGenre;
    private String email;

    public void initData(String searchText, String email) {
        Query.init();
        this.email = email;
        List<List<String>> lst = Query.getAllSong();
        ObservableList<Album> displayList = FXCollections.observableArrayList();
        if (searchText.equals("")) {
            for (List<String> list : lst) {
                String songID = list.get(0);
                Query.init();
                String songName = Query.findSongNamebyID(songID);
                Query.init();
                String artistName = Query.findArtistNamebyID(songID);
                Query.init();
                String genre = Query.findGenrebyID(songID);
                displayList.add(new Album(songName, artistName, genre, songID));
            }
        } else {
            Query.init();
            List<String> listOfID = Query.searchByName(searchText);
            for (String songID : listOfID) {
                Query.init();
                String songName = Query.findSongNamebyID(songID);
                Query.init();
                String artistName = Query.findArtistNamebyID(songID);
                Query.init();
                String genre = Query.findGenrebyID(songID);
                displayList.add(new Album(songName, artistName, genre, songID));
            }

            // List<String> searchArray = Arrays.asList(searchText.split(" "));
            // for (List<String> list : lst) {
            // List<String> filteredList = list.stream().filter(input -> {
            // return searchArray.stream().allMatch(word ->
            // input.toLowerCase().contains(word.toLowerCase()));
            // }).collect(Collectors.toList());
            // songList.getItems().addAll(filteredList);
        }
        songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
        songTable.setItems(displayList);
    }

    public void initialize() {
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    Query.init();
                    String password = Query.getPassword(email);
                    LoginUtil.changeScene(event, "home.fxml", "Home Page", email, password, null);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                try {
                    LoginUtil.changeScene(event, "search.fxml", "Search", email, null, searchText.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        byName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ObservableList<Album> displayList = FXCollections.observableArrayList();
                Query.init();
                List<String> listOfID = Query.searchByName(searchText.getText().trim());
                for (String songID : listOfID) {
                    Query.init();
                    String songName = Query.findSongNamebyID(songID);
                    Query.init();
                    String artistName = Query.findArtistNamebyID(songID);
                    Query.init();
                    String genre = Query.findGenrebyID(songID);
                    displayList.add(new Album(songName, artistName, genre, songID));
                }
                songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
                artistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
                genreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
                songTable.setItems(displayList);
            }

        });

        byArtist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ObservableList<Album> displayList = FXCollections.observableArrayList();
                Query.init();
                List<String> listOfID = Query.searchByArtist(searchText.getText().trim());
                for (String songID : listOfID) {
                    Query.init();
                    String songName = Query.findSongNamebyID(songID);
                    Query.init();
                    String artistName = Query.findArtistNamebyID(songID);
                    Query.init();
                    String genre = Query.findGenrebyID(songID);
                    displayList.add(new Album(songName, artistName, genre, songID));
                }
                songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
                artistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
                genreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
                songTable.setItems(displayList);
            }

        });

        byGenre.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                ObservableList<Album> displayList = FXCollections.observableArrayList();
                Query.init();
                List<String> listOfID = Query.searchByGenre(searchText.getText().trim());
                for (String songID : listOfID) {
                    Query.init();
                    String songName = Query.findSongNamebyID(songID);
                    Query.init();
                    String artistName = Query.findArtistNamebyID(songID);
                    Query.init();
                    String genre = Query.findGenrebyID(songID);
                    displayList.add(new Album(songName, artistName, genre, songID));
                }
                songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
                artistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
                genreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
                songTable.setItems(displayList);
            }

        });

    }
}
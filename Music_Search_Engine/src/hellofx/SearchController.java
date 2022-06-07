package hellofx;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SearchController {
    @FXML
    private ListView<String> songList;
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
        if (searchText.equals("")) {
            for (List<String> list : lst) {
                for (String song : list) {
                    songList.getItems().add(song);
                }
            }
        } else {
            List<String> searchArray = Arrays.asList(searchText.split(" "));
            for (List<String> list : lst) {
                List<String> filteredList = list.stream().filter(input -> {
                    return searchArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
                }).collect(Collectors.toList());
                songList.getItems().addAll(filteredList);
            }
        }
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
                songList.getItems().clear();
                Query.init();
                Query.searchByName(searchText.getText().toLowerCase().trim());
                
            }
            
        });

    }
}
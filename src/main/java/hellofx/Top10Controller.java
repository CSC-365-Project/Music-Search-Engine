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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class Top10Controller {
    @FXML
    private TableView<Album> top10SongTable;
    @FXML
    private TableColumn<Album, String> top10songNameColumn;
    @FXML
    private TableColumn<Album, String> top10ArtistColumn;
    @FXML
    private TableColumn<Album, String> top10GenreColumn;
    @FXML
    private TableColumn<Album, Void> top10AddButton;
    @FXML
    private Button backButton;
    private String email;

    public void initData(String email) {
        Query.init();
        this.email = email;
        List<List<String>> lst = Query.getRecentPopularSong();
        ObservableList<Album> displayList = FXCollections.observableArrayList();

        for (List<String> list : lst) {
            String songID = list.get(0);
            Query.init();
            String songName = Query.findSongNamebyID(songID);
            Query.init();
            String artistName = Query.findArtistNamebyID(songID);
            Query.init();
            String genre = Query.findGenrebyID(songID);
            Query.init();
            String url = Query.getURL(songID);
            displayList.add(new Album(songName, artistName, genre, songID, url));
        }
        top10songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
        top10ArtistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
        top10GenreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
        top10SongTable.setItems(displayList);
        addButtonToTable();
    }

    private void addButtonToTable() {
        Callback<TableColumn<Album, Void>, TableCell<Album, Void>> cellFactory2 = new Callback<TableColumn<Album, Void>, TableCell<Album, Void>>() {
            @Override
            public TableCell<Album, Void> call(final TableColumn<Album, Void> param) {
                final TableCell<Album, Void> cell = new TableCell<Album, Void>() {
                    private final Button btn = new Button("+");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Album data = getTableView().getItems().get(getIndex());
                            Query.init();
                            String songId = data.getSongID();
                            try {
                                LoginUtil.changeSceneInformation(event, "information.fxml", "Information Page", email,
                                        songId);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }

                };
                return cell;
            }
        };

        top10AddButton.setCellFactory(cellFactory2);

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
    }
}
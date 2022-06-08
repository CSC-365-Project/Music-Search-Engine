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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class Top100Controller {
    @FXML
    private TableView<Album> top100SongTable;
    @FXML
    private TableColumn<Album, String> top100songNameColumn;
    @FXML
    private TableColumn<Album, String> top100ArtistColumn;
    @FXML
    private TableColumn<Album, String> top100GenreColumn;
    @FXML
    private TableColumn<Album, Void> top100AddButton;
    @FXML
    private Button backButton;
    private String email;

    public void initData(String email) {
        Query.init();
        this.email = email;
        List<List<String>> lst = Query.getRecentPopularSong();
        ObservableList<Album> displayList = FXCollections.observableArrayList();

        for (List<String> list : lst) {
            // print out the items in the list lst
            System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));

            String songID = list.get(0);
            System.out.println(songID);
            Query.init();
            String songName = Query.findSongNamebyID(songID);
            Query.init();
            String artistName = Query.findArtistNamebyID(songID);
            Query.init();
            String genre = Query.findGenrebyID(songID);
            System.out.println(songName);
            displayList.add(new Album(songName, artistName, genre, songID));
        }
        top100songNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("songName"));
        top100ArtistColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("artistName"));
        top100GenreColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("genre"));
        top100SongTable.setItems(displayList);
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

        top100AddButton.setCellFactory(cellFactory2);

        top100SongTable.getColumns().add(top100AddButton);

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
package hellofx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginUtil {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String email,
            String password, String searchText)
            throws IOException {
        Parent root = null;
        Query.init();

        if (email != null && password != null) {
            if (Query.getPassword(email).compareTo(password) == 0) {
                FXMLLoader loader = new FXMLLoader(LoginUtil.class.getResource(fxmlFile));
                root = loader.load();
                HomeController controller = loader.getController();
                controller.initData(email);
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setTitle("Alert");
                a.setContentText("Password incorrect or user does not exist");
                a.show();
                return;
            }
        } else if (searchText != null) {
            FXMLLoader loader = new FXMLLoader(LoginUtil.class.getResource(fxmlFile));
            root = loader.load();
            SearchController controller = loader.getController();
            controller.initData(searchText, email);

        } else {
            root = FXMLLoader.load(LoginUtil.class.getResource(fxmlFile));
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeSceneTop100(ActionEvent event, String fxmlFile, String title, String email)
            throws IOException {
        System.out.println("In change scene top 100");
        System.out.println("s1");
        Parent root = null;
        System.out.println("s2");
        FXMLLoader loader = new FXMLLoader(LoginUtil.class.getResource(fxmlFile));
        root = loader.load();
        System.out.println("s3");
        Top100Controller controller = loader.getController();
        System.out.println("s4");
        controller.initData(email);
        System.out.println("s5");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("s6");
        stage.setTitle(title);

        System.out.println("s7");
        stage.setScene(new Scene(root, 600, 400));
        System.out.println("s8");
        stage.show();
        System.out.println("s9");
    }

    public static void signUpUser(ActionEvent event, TextField email, TextField username, TextField password,
            TextField password_conf, String fxmlFile) throws IOException {
        Query.init();
        Parent root = null;
        if (!email.getText().trim().isEmpty() && !username.getText().trim().isEmpty()
                && !password.getText().trim().isEmpty()
                && password.getText().compareTo(password_conf.getText()) == 0) {
            if (Query.getPassword(email.getText()).compareTo("") == 0) {
                Query.init();
                Query.addNewUser(email.getText(), username.getText(), password.getText());
                FXMLLoader loader = new FXMLLoader(LoginUtil.class.getResource(fxmlFile));
                root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Login");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setTitle("Alert");
                a.setContentText("Account already exist");
                a.show();
            }
        } else if (password.getText().compareTo(password_conf.getText()) != 0) {
            Alert a = new Alert(AlertType.WARNING);
            a.setTitle("Alert");
            a.setContentText("Password does not match");
            a.show();
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setTitle("Alert");
            a.setContentText("one or more of email, username, or password is blank");
            a.show();
        }

    }
}

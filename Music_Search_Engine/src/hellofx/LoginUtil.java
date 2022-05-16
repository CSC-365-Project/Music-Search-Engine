package hellofx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginUtil {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, TextField email,
            TextField password)
            throws IOException {
        Parent root = null;
        Query.init();

        if (email != null && password != null) {
            if (Query.getPassword(email.getText()).compareTo(password.getText()) == 0) {
                FXMLLoader loader = new FXMLLoader(LoginUtil.class.getResource(fxmlFile));
                root = loader.load();
            } else {
                System.out.println("Password incorrect");
            }
        } else {
            root = FXMLLoader.load(LoginUtil.class.getResource(fxmlFile));
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, TextField email, TextField username, TextField password,
            TextField password_conf) {
        Query.init();
        if (!email.getText().trim().isEmpty() && !username.getText().trim().isEmpty()
                && !password.getText().trim().isEmpty()
                && password.getText().compareTo(password_conf.getText()) == 0) {
            Query.addNewUser(email.getText(), username.getText(), password.getText());
        } else if (password.getText().compareTo(password_conf.getText()) != 0) {
            System.out.println("Password does not match");
        } else {
            System.out.println("one or more of email, username, or password is blank");
        }

    }
}

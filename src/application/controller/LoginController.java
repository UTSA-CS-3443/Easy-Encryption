package application.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import application.Main;
import application.model.Loaders;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView banner;
    @FXML
    private Button login;
    @FXML
    private Circle circle;
    @FXML
    public AnchorPane ap;

    @FXML
    private Text loginFail;

    private Random random;
    public static String currentUser, currentPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Users users = new Users();
        users.loadAllUsers("data/login.csv");
        System.out.println(users.getUserinfo().toString());
        int[] imgNumber = new int[5];
        random = new Random();
        int randIndex = random.nextInt(5);
        for (int i = 0; i < 5; i++)
            imgNumber[i] = i + 1;
        circle.setFill(new ImagePattern(new Image("file:data/icon" + imgNumber[randIndex] + ".png", false)));
    }

    @Override
    public void handle(ActionEvent event) {

    }

    public void handleLogin(ActionEvent event) {
        String inputUsername = username.getText();
        String inputPassword = password.getText();
        if (Main.users.validate("jacob_2", "jhRed")) {
            loginFail.setVisible(false);
            Loaders.loadScene("UserInteraction.fxml");
            LoginController.currentUser = inputUsername;
            LoginController.currentPassword = inputPassword;
        } else {
            loginFail.setVisible(true);
        }
    }
}

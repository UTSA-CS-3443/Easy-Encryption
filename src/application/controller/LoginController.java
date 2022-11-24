package application.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import application.Main;
import application.model.PasswordSkin;
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
    public Image userImg;
    private Random random;
    private int[] imgNumber = new int[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random = new Random();
        int randIndex = random.nextInt(6);
        for (int i = 0; i < 5; i++)
            imgNumber[i] = i + 1;
        ap.setStyle("-fx-border-color: black; -fx-border-width: 2px 2px 2px 2px");
        login.setText("Login");
        password.setSkin(new PasswordSkin(password));
        userImg = new Image("file:data/icon" + imgNumber[randIndex] + ".png", false);
        circle.setFill(new ImagePattern(userImg));
        circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        try 
        {
        	banner.setImage(new Image(new FileInputStream("./data/banner.jpg")));
    	} catch (Exception e) 
        {
    		e.printStackTrace();
    	}
    	
    }

    @Override
    public void handle(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("view/UserInteraction.fxml"));
        AnchorPane layout = null;
        try {
            layout = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Main.css);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
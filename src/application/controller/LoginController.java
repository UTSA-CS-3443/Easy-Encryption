package application.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import application.model.Loaders;
import application.model.PasswordSkin;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private Random random; 
	public static String currentUser, currentPassword;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Users users = new Users();
		users.loadAllUsers("data/login.csv");
		int[] imgNumber = new int[5]; 
		random = new Random();
		int randIndex = random.nextInt(6);
		for (int i=0; i < 5; i++) imgNumber[i] = i + 1; 
		login.setText("Login");
		password.setSkin(new PasswordSkin(password));
		circle.setFill(new ImagePattern( new Image("file:data/icon"+imgNumber[randIndex]+".png", false))); 
		circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
		try { banner.setImage(new Image(new FileInputStream("./data/banner.jpg"))); } 
		catch (Exception e) { e.printStackTrace(); }
	}

	@Override
	public void handle(ActionEvent event) {	
		if (Users.validate(username.getText(), password.getText())) {
			Loaders loader = new Loaders();
			loader.loadSceneIteraction();
			LoginController.currentUser = username.getText();
			LoginController.currentPassword = password.getText();
		}
	}
}

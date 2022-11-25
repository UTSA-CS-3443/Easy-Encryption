package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.Loaders;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class UserInteractionController implements EventHandler<MouseEvent>, Initializable {
	@FXML 
	private AnchorPane outerAp, innerAp;
	@FXML 
	private Label menu;
	@FXML 
	private Button encrypt, decrypt, vault, savedKeys;
	@FXML
	private Circle eCircle, dCircle, vCircle, skCircle;
	private Loaders loader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new Loaders();
		menu.setText("Selection Menu:"); menu.setTextFill(Color.WHITE);
		menu.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 25));
		menu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		outerAp.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
		outerAp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        eCircle.setFill(new ImagePattern(new Image("file:data/encrypt.png", false)));
        eCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.RED));
        dCircle.setFill(new ImagePattern(new Image("file:data/decrypt.png", false)));
        dCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLUE));
        vCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
        vCircle.setFill(new ImagePattern(new Image("file:data/vault.jpg", false)));
        skCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
        skCircle.setFill(new ImagePattern(new Image("file:data/keys.png", false)));
        encrypt.setOnAction(event -> loader.loadSceneEncrypt());
        decrypt.setOnAction(event -> loader.loadSceneDecrypt());
        vault.setOnAction(event -> loader.loadSceneVault());
        savedKeys.setOnAction(event -> loader.loadSceneKeys());
	}

	@Override
	public void handle(MouseEvent event) {
		Circle circle = (Circle) event.getSource();
		String circleText = circle.getId();
				
		if (circleText.equals("eCircle")) loader.loadSceneEncrypt();
		else if (circleText.equals("dCircle")) loader.loadSceneDecrypt();
		else if (circleText.equals("vCircle")) loader.loadSceneVault();
		else if (circleText.equals("skCircle")) loader.loadSceneKeys();
	} 
	
	
}

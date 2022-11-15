package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Loaders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SavedKeysController implements EventHandler<ActionEvent>, Initializable {
	@FXML
	private AnchorPane outerAp, innerAp;
	@FXML
	private HBox buttons;
	@FXML
	private Button encrypt, decrypt, vault, savedKeys;
    @FXML private Label state; 


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		outerAp.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
		outerAp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		buttons.setSpacing(10);
        buttons.setPadding(new Insets(15, 15, 15, 18));
        buttons.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        innerAp.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        state.setText("saved keys test");
	}
	@Override
	public void handle(ActionEvent event) {
		Loaders loader = new Loaders();
		Button button = (Button) event.getSource();
		String buttonText = button.getText();
		
		if (buttonText.equals("Encrypt")) loader.loadSceneEncrypt();
		else if (buttonText.equals("Decrypt")) loader.loadSceneDecrypt();
		else if (buttonText.equals("Vault")) loader.loadSceneVault();
	}

}

package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Loaders;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    @FXML
    private Label state;
    @FXML
    private ListView<String> keys;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Users users = new Users();
        users.addSavedKeys("data/login.csv");
        keys.getItems().addAll(users.getSavedKeys());
    }

    @Override
    public void handle(ActionEvent event) {
        Loaders loader = new Loaders();
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Encrypt"))
            Loaders.loadScene("EncryptView1.fxml");
        else if (buttonText.equals("Decrypt"))
            Loaders.loadScene("DecryptView1.fxml");
        else if (buttonText.equals("Vault"))
            Loaders.loadScene("VaultView1.fxml");
    }

}

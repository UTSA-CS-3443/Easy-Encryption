
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Loaders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class UserInteractionController implements Initializable {
    @FXML
    private AnchorPane outerAp, innerAp;
    @FXML
    private HBox buttons;
    @FXML
    private Button encrypt, decrypt, vault, savedKeys, home;
    @FXML
    private Circle eCircle, dCircle, vCircle, skCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
    }

    public void handle(ActionEvent event) {

        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Encrypt"))
            Loaders.loadScene("EncryptView1.fxml");
        else if (buttonText.equals("Decrypt"))
            Loaders.loadScene("DecryptView1.fxml");
        else if (buttonText.equals("Vault"))
            Loaders.loadScene("VaultView1.fxml");
        else if (buttonText.equals("Saved Keys"))
            Loaders.loadScene("SavedKeysView1.fxml");
        else if (buttonText.equals("Home"))
            Loaders.loadScene("UserInteraction.fxml");
    }

    /**
     * handleCircle handle mouse events generated from the 4 circles.
     * 
     * @param e
     */
    public void handleCircle(MouseEvent e) {
        Circle circle = (Circle) e.getSource();
        String circleText = circle.getId();

        if (circleText.equals("eCircle"))
            Loaders.loadScene("EncryptView1.fxml");
        else if (circleText.equals("dCircle"))
            Loaders.loadScene("DecryptView1.fxml");
        else if (circleText.equals("vCircle"))
            Loaders.loadScene("VaultView1.fxml");
        else if (circleText.equals("skCircle"))
            Loaders.loadScene("SavedKeysView1.fxml");
    }

    /**
     * Cleans up code in initialize method. Sets images within each circle.
     * 
     * 
     */
    public void setImages() {
        eCircle.setFill(new ImagePattern(new Image("file:data/encrypt.png", false)));
        eCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.RED));
        dCircle.setFill(new ImagePattern(new Image("file:data/decrypt.png", false)));
        dCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLUE));
        vCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
        vCircle.setFill(new ImagePattern(new Image("file:data/vault.jpg", false)));
        skCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
        skCircle.setFill(new ImagePattern(new Image("file:data/keys.png", false)));
    }
}
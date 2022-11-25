package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import application.Main;
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
import javafx.stage.FileChooser;

public class EncryptController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    private AnchorPane apParent, ap1, ap2, apChild1, apChild2;
    @FXML
    private HBox buttons;
    @FXML
    private Button encrypt, decrypt, vault, savedKeys, fopen;
    @FXML
    private Label regFileContent, encryptFileContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apParent.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        apParent.setBackground(new Background(
                new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(30, 30, 30, 18));
        //buttons.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        buttons.getStyleClass().add("hbox");
        ap1.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        ap2.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        apChild1.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        apChild2.setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Decrypt"))
            Loaders.loadScene("DecryptView1.fxml");
        else if (buttonText.equals("Vault"))
            Loaders.loadScene("VaultView1.fxml");
        else if (buttonText.equals("Saved Keys"))
            Loaders.loadScene("SavedKeysView1.fxml");
        else if (buttonText.equals("Open File")) {
            FileChooser fChooser = new FileChooser();
            File file = fChooser.showOpenDialog(Main.primaryStage);

            Scanner scan = null;
            StringBuilder bookText = new StringBuilder();

            try {
                scan = new Scanner(new File(file.getAbsolutePath()));

                while (scan.hasNextLine()) {
                    bookText.append(scan.nextLine());
                    bookText.append("\n");
                }
                scan.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            regFileContent.setText(bookText.toString());

            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
                SecretKey desKey = keyGenerator.generateKey();
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(Cipher.ENCRYPT_MODE, desKey);

                byte[] fileContent = bookText.toString().getBytes("UTF8");
                byte[] fileEncryption = cipher.doFinal(fileContent);
                encryptFileContent.setText(new String(fileEncryption));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

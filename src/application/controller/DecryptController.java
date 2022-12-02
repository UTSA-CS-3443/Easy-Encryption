package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import application.Main;
import application.model.CryptoUtils;
import application.model.Loaders;
import application.model.Users;
import application.model.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class DecryptController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    private AnchorPane apParent, ap1, ap2, apChild1, apChild2;
    @FXML
    private HBox buttons;
    @FXML
    private Label textInput, textOutput;
    @FXML
    private Button encrypt, decrypt, vault, savedKeys, fopen, home;
    @FXML
    private TextField keyInput;
    private StringBuilder bookText = null;
    private File file = null;
    private boolean isFileOpen = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isFileOpen = false;
    }

    String decryptRes;

    public void handleDecrypt(ActionEvent event) {
        if (!isFileOpen) {
            System.out.print(CryptoUtils.readEncrypted(file));
        }
        try {
            File decryptedOutFile;

            if (keyInput.getText().length() == 16) {
                String key = keyInput.getText();
                if (file.getPath().lastIndexOf("encrypted.txt") != -1) {
                    decryptedOutFile = new File(file.getPath().substring(0, file.getPath().lastIndexOf("encrypted.txt"))
                            + ".decrypted.txt");
                } else {
                    decryptedOutFile = new File(file.getPath() + ".decrypted.txt");
                }
                CryptoUtils.decrypt(key, file, decryptedOutFile);
                decryptRes = CryptoUtils.readEncrypted(decryptedOutFile);
                textOutput.setText(decryptRes);
            } else {
                textOutput.setText("ERROR: Invalid key (16 char)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Loads the appropriate view
    * If Open File is selected a txt file will be read
    * and displayed
    */
    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Encrypt"))
            Loaders.loadScene("EncryptView1.fxml");
        else if (buttonText.equals("Vault"))
            Loaders.loadScene("VaultView1.fxml");
        else if (buttonText.equals("Saved Keys"))
            Loaders.loadScene("SavedKeysView1.fxml");
        else if (buttonText.equals("Home"))
            Loaders.loadScene("UserInteraction.fxml");
        else if (buttonText.equals("Open File")) {
            FileChooser fChooser = new FileChooser();
            file = fChooser.showOpenDialog(Main.primaryStage);
            Scanner scan = null;
            bookText = new StringBuilder();

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

            textInput.setText(CryptoUtils.readEncrypted(file));
            isFileOpen = true;
        }
    }
}

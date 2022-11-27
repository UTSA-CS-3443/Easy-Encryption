package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.crypto.SecretKey;
import application.Main;
import application.model.Encryption;
import application.model.Loaders;
import application.model.Users;
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

import application.model.CryptoUtils;

public class EncryptController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    private AnchorPane apParent, ap1, ap2, apChild1, apChild2;
    @FXML
    private HBox buttons;
    @FXML
    private Label textInput, textOutput;
    @FXML
    private TextField keyInput;
    @FXML
    private Button encrypt, decrypt, vault, savedKeys, fopen, home;
    private StringBuilder bookText = null;
    private File file = null;
    private boolean isFileOpen = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isFileOpen = false;
    }

    String encryptRes;

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        
        System.out.println(buttonText);

        if (buttonText.equals("Decrypt"))
            Loaders.loadScene("DecryptView1.fxml");
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

            textInput.setText(bookText.toString());
            isFileOpen = true;
        }

        else if (buttonText.equals("Encrypt")) {
            if (!isFileOpen) {
                System.out.print(bookText.toString());
            }
            try {
                
                Users users = new Users();
                String doesFileExist = users.doesFileExist("data/login.csv", file.getName());
                
                if (doesFileExist == null) {
                  if(keyInput.getText().length()==16)
                  {
                  String key = keyInput.getText();
                  File encryptedOutFile = new File(file.getPath()+".encrypted.txt");
                  CryptoUtils.encrypt(key, file, encryptedOutFile);
                  System.out.print("This is the cryptoUtils test:"+CryptoUtils.readEncrypted(encryptedOutFile));
                  encryptRes = CryptoUtils.readEncrypted(encryptedOutFile);
                  textOutput.setText(encryptRes);
                  users.addKeyAndFile("data/login.csv", LoginController.currentUser,
                          "," + file.getName() + "," + key);
                  }
                  else
                  {
                      
                      textOutput.setText("ERROR: Invalid key (16 char)");
                  }
                } else
                    textOutput.setText(doesFileExist);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

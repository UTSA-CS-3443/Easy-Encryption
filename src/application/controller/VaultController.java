package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Main;
import application.model.CryptoException;
import application.model.CryptoUtils;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;

public class VaultController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    private AnchorPane outerAp, innerAp;
    @FXML
    private HBox buttons;
    @FXML
    private Button encrypt, decrypt, vault, savedKeys, home, save;
    @FXML
    private TextArea notepad;
    @FXML
    private Label state;
    private StringBuilder bookText = null;
    private File encryptedVault = null;
    private File decryptedVault;
    private Scanner scan = null;
    private String key = "Vaultkeyencrypt1";
    private Users users = new Users();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scanner scan = null;
        encryptedVault = new File("vault/"+Main.users.getCurUser()+"EncryptedVault.txt");
        decryptedVault = new File("vault/"+Main.users.getCurUser()+"DecrypedVault.txt");
        bookText = new StringBuilder();

        try {
                    CryptoUtils.decrypt(key, encryptedVault, decryptedVault);
            scan = new Scanner(new File(decryptedVault.getAbsolutePath()));

            while (scan.hasNextLine()) {
                bookText.append(scan.nextLine());
                bookText.append("\n");
            }
            scan.close();
        } catch (IOException | CryptoException e) {
            
            e.printStackTrace();
        }

        notepad.setText(bookText.toString());
        
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Encrypt"))
        {
            Loaders.loadScene("EncryptView1.fxml");
            try {
                Files.deleteIfExists(decryptedVault.toPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            else if (buttonText.equals("Decrypt"))
        {
            Loaders.loadScene("DecryptView1.fxml");
            try {
                Files.deleteIfExists(decryptedVault.toPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if (buttonText.equals("Saved Keys"))
        {
            Loaders.loadScene("SavedKeysView1.fxml");
            try {
                Files.deleteIfExists(decryptedVault.toPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            else if (buttonText.equals("Home"))
        {
            Loaders.loadScene("UserInteraction.fxml");
            try {
                Files.deleteIfExists(decryptedVault.toPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if (buttonText.equals("save")){
            try {
                Files.deleteIfExists(encryptedVault.toPath());
                Files.deleteIfExists(decryptedVault.toPath());
                FileWriter write = new FileWriter(decryptedVault);
                write.write(notepad.getText());
                write.close();
                CryptoUtils.encrypt(key, decryptedVault, encryptedVault);
                }
                catch (IOException | CryptoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

}

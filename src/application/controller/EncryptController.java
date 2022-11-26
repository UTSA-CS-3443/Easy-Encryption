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
    private TextArea textInput, textOutput;
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
                // " salt " explanation
                // https://en.wikipedia.org/wiki/Salt_(cryptography)

                Users users = new Users();
                String doesFileExist = users.doesFileExist("data/login.csv", file.getName());

                if (doesFileExist == null) {
                    Encryption encryption = new Encryption();
                    SecureRandom random = new SecureRandom();
                    byte bytes[] = new byte[20];
                    random.nextBytes(bytes);

                    SecretKey secretKey = Encryption.getKeyFromPassword(LoginController.currentPassword,
                            bytes.toString());
                    encryptRes = encryption.encrypt(bookText.toString(), secretKey);
                    textOutput.setText(encryptRes);
                    users.addKeyAndFile("data/login.csv", LoginController.currentUser,
                            "," + file.getName() + "," + encryptRes);
                } else
                    textOutput.setText(doesFileExist);

                // String d = encryption.decrypt(encryptRes, secretKey, 128);
                // test.setText(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

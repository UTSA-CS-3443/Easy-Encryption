/**
 * Loaders.java is a collection of loader methods to change scenes in the controllers.
 * For EasyEncryption Project
 * Author: Daniel Botello IV
 * 
 */
package application.model;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class Loaders {

    /**
     * This is the first implementation i can think of. Takes in a String and
     * appends to the path of file. This is probably best as this method can work
     * with many scenes beyond what our project requires.
     * 
     * @param file
     */
    public static void loadScene(String file) {
        try {
            AnchorPane root = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/" + file));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);

            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
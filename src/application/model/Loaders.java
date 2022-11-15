package application.model;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Loaders {
    
    //Constructor
    public Loaders() {
        /*Nothing goes here.
         * To use in Controller scenes:
         *  1. Create new loaders object (loaders load = new loaders();)
         *  2. Call any of the method below on that object.
         *  3.Boom your good.
         *  Tested out on my Lab 5 project. Worked well.
         *  
         */
    }
    
    /**
     * This is the first implementation i can think of.
     * Takes in a String and appends to the path of file.
     * This is probably best as this method can work with many scenes beyond what our project requires.
     * 
     * @param file
     */
    public void loadScene(String file) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/" + file));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }       
    }
    
    /*These next methods are the second implementation.
     * They dont require a String to be passed. However, the file paths are hardcoded.
     *  
    */
    
    /*
     * 
     * Loads Encrypt view scene.
     * TODO: Update name of scene if different.
     */
    public void loadSceneEncrypt() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/EncryptView.fxml"));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Loads decrypt view scene
     * TODO: Update name of scene if different.
     */
    public void loadSceneDecrypt() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/DecryptView.fxml"));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * loads vault scene.
     * TODO: Update name of scene if different.
     */
    public void loadSceneVault() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/VaultView.fxml"));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * loads Saved keys scene.
     * TODO: Update name of scene if different.
     */
    public void loadSceneKeys() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/SavedKeysView.fxml"));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * loads the login scene, call when log off button is pressed.
     * TODO: Update name of scene if different.
     */
    public void loadSceneLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
            Main.primaryStage.setScene(new Scene(root, 800, 800));
            Main.primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
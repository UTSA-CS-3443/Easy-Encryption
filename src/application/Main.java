package application;

import java.io.IOException;
import application.controller.LoginController;
import application.model.Users;
import application.model.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    public static Stage primaryStage;
    public static BorderPane layout;
    public static String css;
    public static Users users;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        users = new Users();
        try {
            primaryStage = stage;

            primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    System.out.println(Utils.getRandString(16));
                    if (users.getCurUser() != null) {
                        System.out.println("Saving user data");
                        users.serializeUserData();
                    }
                    Platform.exit();
                    System.exit(0);
                }
            });

            BorderPane root = new BorderPane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Login.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

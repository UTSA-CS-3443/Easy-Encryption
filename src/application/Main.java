package application;

import java.io.IOException;
import application.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    public static Stage primaryStage;
    public static BorderPane layout;
    public static String css;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Login.fxml"));
        try {
            Main.layout = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.layout
                .setStyle("-fx-border-color: black; -fx-border-width: 3px 3px 3px 3px");
        LoginController controller = loader.getController();
        Main.layout.setCenter(controller.ap);
        Scene scene = new Scene(Main.layout);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        Main.primaryStage = primaryStage;
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The Main class test
 * 
 * @author Jacob Houssian
 * @author Abdurrahmaan Baghdadi
 * @author Preaston Cunningham
 * @author Daniel Botello IV
 */
public class Main extends Application {
	//Makes stage available everywhere.
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			//Connect to the FXML and load it in
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/view/LoginView.fxml"));
			AnchorPane layout = (AnchorPane) loader.load();
			//Put the layout onto the scene
			Scene scene = new Scene( layout );
			//Load CSS
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			//Set the scene on the stage
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

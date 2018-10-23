package Ap.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	public static void main(String[] args) throws Exception {

		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Pane.fxml"));
		AnchorPane stackPane = loader.load();
		Scene scene = new Scene(stackPane, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Race Simulator");
		primaryStage.show();
	}
}

/*
 * author 	: Amit Patil
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			stage 			= primaryStage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
//			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
//			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
			Scene scene 	= new Scene(root);
			stage.setTitle("Login View");
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
}





//package application;
//	
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//
//
//public class Main extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}

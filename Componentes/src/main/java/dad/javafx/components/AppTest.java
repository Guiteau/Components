package dad.javafx.components;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppTest extends Application{
	
	DateChooser dateComponent;
	VBox root;
	HBox buttonHBox;
	Button startButton, queryButton;

	@Override
	public void start(Stage primaryStage) throws Exception {

		dateComponent = new DateChooser();		
		
		startButton = new Button("Inicializar");
		queryButton = new Button("Consultar");
		
		buttonHBox = new HBox(startButton, queryButton);
		
		buttonHBox.setSpacing(10);
		buttonHBox.setAlignment(Pos.CENTER);
		
		root = new VBox(dateComponent, buttonHBox);
		
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
	
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Date component tester");

		primaryStage.setScene(scene);

		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}

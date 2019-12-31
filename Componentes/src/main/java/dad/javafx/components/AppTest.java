package dad.javafx.components;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppTest extends Application {

	DateChooser dateComponent;
	VBox root;
	HBox buttonHBox;
	Button startButton, queryButton;

	@Override
	public void start(Stage primaryStage) throws Exception {

		dateComponent = new DateChooser();

		startButton = new Button("Inicializar");
		startButton.setOnAction(e -> onStartButtonAction(e));

		queryButton = new Button("Consultar");
		queryButton.setOnAction(e -> onQueryButtonAction(e));

		buttonHBox = new HBox(startButton, queryButton);

		buttonHBox.setSpacing(10);
		buttonHBox.setAlignment(Pos.CENTER);

		root = new VBox(dateComponent.getView(), buttonHBox);

		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 420, 200);

		primaryStage.setTitle("Date component tester");

		primaryStage.setScene(scene);

		primaryStage.show();

	}

	private void onQueryButtonAction(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fecha");
		alert.setHeaderText(null);
		alert.setContentText("La fecha seleccionada es: "+dateComponent.getDataDefined());
		alert.showAndWait();
		
	}
	
	private void onStartButtonAction(ActionEvent e) {

		dateComponent.setTodayData();

	}

	public static void main(String[] args) {
		launch(args);
	}

}

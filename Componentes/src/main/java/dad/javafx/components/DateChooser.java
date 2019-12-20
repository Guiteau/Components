package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class DateChooser extends HBox implements Initializable {

	// MODEL

	private ArrayList<String> yearsArray = new ArrayList<String>();
	private ArrayList<String> monthsArray = new ArrayList<String>(12);

	ListProperty<Integer> dayList = new SimpleListProperty<Integer>();
	ListProperty<String> monthList = new SimpleListProperty<String>();
	ListProperty<String> yearList = new SimpleListProperty<String>();
	
	private boolean anioBisiesto = false;
	
	private ObjectProperty<LocalDate> dateFecha = new SimpleObjectProperty<>();

	// VIEW

	@FXML
	private HBox view;

	@FXML
	private ComboBox<Integer> daysCombo;

	@FXML
	private ComboBox<String> monthsCombo;

	@FXML
	private ComboBox<String> yearsCombo;

	public DateChooser() {

		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateChooserView.fxml"));
			loader.setController(this);
			// loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		monthsArray.add("Enero");
		monthsArray.add("Febrero");
		monthsArray.add("Marzo");
		monthsArray.add("Abril");
		monthsArray.add("Mayo");
		monthsArray.add("Junio");
		monthsArray.add("Julio");
		monthsArray.add("Agosto");
		monthsArray.add("Septiembre");
		monthsArray.add("Octubre");
		monthsArray.add("Noviembre");
		monthsArray.add("Diciembre");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/*
		 * int year = localDate.getYear(); 
		 * int month = localDate.getMonthValue(); 
		 * int day = localDate.getDayOfMonth(); }
		 */

		for (int i = LocalDate.now().getYear(); i >= 1900; i--) { // de este modo partimos del año actual hasta 1900

			yearsArray.add(Integer.toString(i));

		}

		yearList.addAll(yearsArray);
		
		monthList.addAll(monthsArray);
		
		

	}

}

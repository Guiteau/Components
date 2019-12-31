package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class DateChooser extends HBox implements Initializable {

	// MODEL

	private ArrayList<String> yearsArray = new ArrayList<String>();
//	private ArrayList<String> monthsArray = new ArrayList<String>(12);

	private ArrayList<String> monthsArray = new ArrayList<>(Arrays.asList("Enero", "Febrero", "Marzo", "Abril",

			"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));

	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();

	ListProperty<Integer> daysList = new SimpleListProperty<>(FXCollections.observableArrayList());

	// ObservableList: A list that enables listeners to track changes when they
	// occur

	// FXCollections: A utility class that consists of static methods that are
	// one-to-one copies
	// of java.util.Collections methods

	// As a developer, what does this mean?

	// This allows controls that use lists (tables, combo boxes) to monitor for
	// changes to that list and update
	// the display automatically. Don’t forget to create public accessors for the
	// observable list. The real power
	// of ObservableCollections is in the fact that when you update the list, the
	// GUI widget is automatically
	// updated to reflect the new list!

	// Es necesario que hagamos uso de este tipo de listas para tener en cuenta los
	// cambios de fecha

	ListProperty<String> monthsList = new SimpleListProperty<>(FXCollections.observableArrayList());

	ListProperty<String> yearsList = new SimpleListProperty<>(FXCollections.observableArrayList());

	private boolean anioBisiesto = false;

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
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/*
		 * int year = localDate.getYear(); int month = localDate.getMonthValue(); int
		 * day = localDate.getDayOfMonth(); }
		 */

		for (int i = LocalDate.now().getYear(); i >= 1900; i--) { // de este modo partimos del año actual hasta 1900

			yearsArray.add(Integer.toString(i));

		}

		monthsList.addAll(monthsArray);
		monthsCombo.setItems(monthsList); // incluímos el conjunto del arrayList
		monthsCombo.getSelectionModel().selectFirst();

		monthsGenerator(); // de este modo no saldrá vacío el campo de días en un primer momento

		daysCombo.getSelectionModel().selectFirst(); // aparecerá el primero siempre

		monthsCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {  // para captar los cambios según el año
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				monthsGenerator();

			}
		});

		yearsList.addAll(yearsArray); // añadimos todo el listado del array
		yearsCombo.setItems(yearsList);
		yearsCombo.getSelectionModel().selectFirst();

		yearsCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				
				if(newValue.contentEquals("")) {

					yearsCombo.getSelectionModel().selectFirst();
					
				}else {
					
					yearsGenerator(oldValue, newValue);
					
				}

			}
		});

	}

	public void daysGenerator(int lastDay) {

		daysCombo.getItems().clear(); // vacíamos el combo
		daysList.clear(); // vacíamos el array

		for (int i = 1; i <= lastDay; i++) {

			daysList.add(i);

		}

		daysCombo.setItems(daysList); // volvemos a completarlo con los nuevos días
		daysCombo.getSelectionModel().selectFirst(); // de nuevo que aparezca un valor de entrada

	}

	@SuppressWarnings("unused")
	public void monthsGenerator() {

		daysGenerator(Month.of(monthsCombo.getSelectionModel().getSelectedIndex() + 1).length(anioBisiesto));

		// getSelectedIndex() empezará en 0, hay que añadir 1 (1 - 12)

	}

	public void yearsGenerator(String ov, String nv) {

		try {

			if (Integer.parseInt(nv) < 1) {

				throw new RuntimeException("Valor no válido"); // la vamos a considerar como excepción en tiempo de
																// ejecución

			}

		} catch (RuntimeException e) {

			yearsCombo.setValue(ov);

		}

		anioBisiesto = (Year.of(Integer.parseInt(yearsCombo.getValue())).isLeap()) ? true : false;

		monthsGenerator();

	}

	public final ObjectProperty<LocalDate> datePropertyProperty() {
		return this.dateProperty;
	}

	public final LocalDate getDateProperty() {
		return this.datePropertyProperty().get();
	}

	public final void setDateProperty(final LocalDate dateProperty) {
		this.datePropertyProperty().set(dateProperty);
	}
	
	public HBox getView() {
		return view;
	}

	public void setTodayData() {
		
		int monthNumber = LocalDate.now().getMonthValue();
		
		this.daysCombo.setValue(LocalDate.now().getDayOfMonth());
		this.monthsCombo.setValue(monthsArray.get(monthNumber-1));	
		this.yearsCombo.setValue(Integer.toString(LocalDate.now().getYear()));
		
	}

	public String getDataDefined() {
		
		String data="";
		
		data+=daysCombo.getSelectionModel().getSelectedItem()+"/";
		data+=(monthsCombo.getSelectionModel().getSelectedIndex()+1)+"/";
		data+=yearsCombo.getSelectionModel().getSelectedItem();
		
		return data;
	}
	
}

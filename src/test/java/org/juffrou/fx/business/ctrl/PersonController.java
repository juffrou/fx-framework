package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PersonController implements Initializable {
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private void save() {
		System.out.println("save");
	}
	
	@FXML
	private void cancel() {
		System.out.println("cancel");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Bind this controller with the Person Presentation Model
		
	}

}

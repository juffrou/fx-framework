package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.business.pm.PersonPM;

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
		bindPresentationModel();
	}
	
	private void unbindPresentationModel() {
		//TODO must unbind before changing the domain
	}

	private void bindPresentationModel() {
		Person personDom = new Person();
		personDom.setName("Carlos");
		
		PersonPM personFx = new PersonPM(personDom);
		
		name.textProperty().bindBidirectional(personFx.nameProperty());
		email.textProperty().bindBidirectional(personFx.emailProperty());
		dateOfBirth.valueProperty().bindBidirectional(personFx.dateOfBirthProperty());

	}
}

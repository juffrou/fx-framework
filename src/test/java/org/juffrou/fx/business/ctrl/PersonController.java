package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.business.pm.PersonPM;
import org.juffrou.fx.presentationmodel.BasePresentationModel;

public class PersonController implements Initializable {
	
	BasePresentationModel<Person> basePresentationModel;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private void save() {
		System.out.println("save");
		Person newPresentationModelDomain = basePresentationModel.getNewPresentationModelDomain();
		System.out.println(newPresentationModelDomain.getName());

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
		
		basePresentationModel = new BasePresentationModel<Person>(Person.class);
		
		basePresentationModel.bindReadWrite(name.textProperty(), "name");
		basePresentationModel.bindReadWrite(email.textProperty(), "email");
		basePresentationModel.bindReadWrite(dateOfBirth.valueProperty(), "dateOfBirth");
		
		basePresentationModel.setNewPresentationModelDomain(personDom);
		
	}
	
	private void old_bindPresentationModel() {
		Person personDom = new Person();
		personDom.setName("Carlos");
		
		PersonPM personFx = new PersonPM(personDom);
		
		name.textProperty().bindBidirectional(personFx.nameProperty());
		email.textProperty().bindBidirectional(personFx.emailProperty());
		dateOfBirth.valueProperty().bindBidirectional(personFx.dateOfBirthProperty());

	}
}

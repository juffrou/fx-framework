package org.juffrou.fx.business.ctrl;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.presentationmodel.ControllerModel;

public class PersonController extends BeanController<Person> {
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private void save() {
		System.out.println("save");
		Person person = getControllerModel().getModelSource();
		System.out.println("name: " + person.getName());
		System.out.println("email: " + person.getEmail());
		System.out.println("date of birth: " + person.getDateOfBirth());
	}
	
	@FXML
	private void cancel() {
		System.out.println("cancel");
		
		Person person = new Person();
		person.setName("Carlos");
		person.setEmail("cemartins@netcabo.pt");
		person.setDateOfBirth(LocalDate.of(1967, 10, 1));
		getControllerModel().setModelSource(person);

	}

	
	public void bindControllerModel(ControllerModel<Person> presentationModel) {

		presentationModel.bindReadWrite(name.textProperty(), "name");
		presentationModel.bindReadonly(email.textProperty(), "email");
		presentationModel.bindReadWrite(dateOfBirth.valueProperty(), "dateOfBirth");
	}
	
}

package org.juffrou.fx.business.ctrl;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.model.BeanControllerModel;

public class PersonController extends BeanController<Person> {
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	
	public static FXMLLoader getLoader() {
		URL url = PersonController.class.getResource("/org/juffrou/fx/business/Person.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	

	public PersonController() {
		super(Person.class);
	}

	public void bindControllerModel(BeanControllerModel<Person> presentationModel) {

		presentationModel.bindReadWrite(name.textProperty(), "name");
		presentationModel.bindReadonly(email.textProperty(), "email");
		presentationModel.bindReadWrite(dateOfBirth.valueProperty(), "dateOfBirth");
	}
	
}

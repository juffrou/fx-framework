package org.juffrou.fx.business.ctrl;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.presentationmodel.ControllerModel;

public class ContactTableController extends TableController<Contact> {
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	
	public static FXMLLoader getLoader() {
		URL url = ContactTableController.class.getResource("/org/juffrou/fx/business/Person.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	

	public ContactTableController() {
		super(Contact.class);
	}

	
	public void bindControllerModel(ControllerModel<Contact> presentationModel) {

		presentationModel.bindReadWrite(name.textProperty(), "name");
		presentationModel.bindReadonly(email.textProperty(), "email");
		presentationModel.bindReadWrite(dateOfBirth.valueProperty(), "dateOfBirth");
	}
	
}

package org.juffrou.fx.business.ctrl;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.model.BeanControllerModel;

public class ContactController extends BeanController<Contact> {
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField value;
	
	
	public static FXMLLoader getLoader() {
		URL url = ContactController.class.getResource("/org/juffrou/fx/business/Contact.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	

	public ContactController() {
		super(Contact.class);
	}

	public void bindControllerModel(BeanControllerModel<Contact> presentationModel) {

		presentationModel.readWriteBind(description.textProperty(), "description");
		presentationModel.readWriteBind(value.textProperty(), "value");
	}
	
}

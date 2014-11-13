package org.juffrou.fx.business.ctrl;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.controller.model.TableControllerModel;

public class ContactTableController extends TableController<Contact> {
	
	@FXML
	private TableView<Contact> table;
	
	
	public static FXMLLoader getLoader() {
		URL url = ContactTableController.class.getResource("/org/juffrou/fx/business/Contact.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	

	public ContactTableController() {
		super(Contact.class);
	}

	
	public void bindControllerModel(TableControllerModel<Contact> presentationModel) {

		table.setItems(getControllerModel().getModelSource());
	}
	
}

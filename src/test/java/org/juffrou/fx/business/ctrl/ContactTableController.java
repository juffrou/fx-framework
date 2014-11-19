package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.controller.model.TableControllerModel;

public class ContactTableController extends TableController<Contact> {
	
	@FXML
	private TableView<Contact> table;
	
	@FXML
	private TableColumn<Contact,String> valueColumn;
	
	
	public static FXMLLoader getLoader() {
		URL url = ContactTableController.class.getResource("/org/juffrou/fx/business/ContactTable.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	

	public ContactTableController() {
		super(Contact.class);
	}

	
	public void bindControllerModel(TableControllerModel<Contact> presentationModel) {

		table.setItems(getControllerModel().getModelSource());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		// add editable cell factory for the value column
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}
}

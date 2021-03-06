package org.juffrou.fx.business.pm;

import java.io.IOException;
import java.time.LocalDate;

import org.juffrou.fx.business.ctrl.ContactTableController;
import org.juffrou.fx.business.ctrl.PersonController;
import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.ControllerFactory;
import org.juffrou.fx.core.LifecyclePresentationManager;
import org.juffrou.fx.error.NodeBuildingException;
import org.juffrou.fx.serials.FxSerialsContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class PersonPM implements LifecyclePresentationManager {
	
	private FxSerialsContext fxSerialsContext = new FxSerialsContext();
	private BeanController<Person> personController;
	private ContactTableController contactController;
	
	public PersonPM() {
	}
	
	public Parent getNode() {
		
		try {
			
			VBox vbox = new VBox();
			
			//Load person 
			FXMLLoader loader = ControllerFactory.getLoader(PersonController.FXML_PATH);
			loader.load();
			Parent parent = loader.getRoot();
			personController = loader.getController();
			vbox.getChildren().add(parent);

			// Load contacts
			loader = ControllerFactory.getLoader(ContactTableController.FXML_PATH);
			parent = loader.load();
			contactController = loader.getController();
			vbox.getChildren().add(parent);

			//Bind the contacts controller supporting property to the "contacts" property of the person bean
			personController.getBinder().addBidirectional(contactController.getControllerModel(), "contacts");

			return vbox;
		} catch (IOException e) {
			throw new NodeBuildingException("Cannot load Person.fxml", e);
		}
	}
	
	public void save() {
		System.out.println("saving person");
		Person person = personController.getControllerModel().getModelSource();
		System.out.println("name: " + person.getName());
		System.out.println("email: " + person.getEmail());
		System.out.println("date of birth: " + person.getDateOfBirth());
		System.out.println("Contacts: " + person.getContacts());
	}
	
	public void cancel() {
		System.out.println("loading person");
		
		Person person = new Person();
		person.setName("Carlos");
		person.setEmail("cemartins@netcabo.pt");
		person.setDateOfBirth(LocalDate.of(1967, 10, 1));
		
		Contact contact = new Contact();
		contact.setDescription("phone");
		contact.setValue("21 441 97 53");
		person.addContact(contact);
		
		contact = new Contact();
		contact.setDescription("mobile");
		contact.setValue("916 173 239");
		person.addContact(contact);
		
		Person proxy = fxSerialsContext.getProxy(person);
		
		personController.getControllerModel().setModelSource(proxy);

	}

}

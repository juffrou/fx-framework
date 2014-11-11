package org.juffrou.fx.business.pm;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.juffrou.fx.business.ctrl.PersonController;
import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.core.LifecycleController;
import org.juffrou.fx.core.LifecyclePresentationManager;
import org.juffrou.fx.error.NodeBuildingException;

public class PersonPM implements LifecyclePresentationManager {
	
	private BeanController<Person> personController;
	
	public PersonPM() {
	}
	
	public Parent getNode() {
		
		try {
			
			//Load person 
			FXMLLoader loader = PersonController.getLoader();
			loader.load();
			Parent parent = loader.getRoot();
			personController = loader.getController();
			personController.bind();
			
			return parent;
		} catch (IOException e) {
			throw new NodeBuildingException("Cannot load Person.fxml", e);
		}
	}
	
	public void save() {
		System.out.println("save");
		Person person = personController.getControllerModel().getModelSource();
		System.out.println("name: " + person.getName());
		System.out.println("email: " + person.getEmail());
		System.out.println("date of birth: " + person.getDateOfBirth());
	}
	
	public void cancel() {
		System.out.println("cancel");
		
		Person person = new Person();
		person.setName("Carlos");
		person.setEmail("cemartins@netcabo.pt");
		person.setDateOfBirth(LocalDate.of(1967, 10, 1));
		personController.getControllerModel().setModelSource(person);

	}

}

package org.juffrou.fx.business.pm;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.core.LifecyclePresentationManager;
import org.juffrou.fx.error.NodeBuildingException;

public class PersonPM implements LifecyclePresentationManager {
	
	private BeanController<Person> personController;
	
	public PersonPM() {
	}
	
	public Parent getNode() {
		
		try {
			
			//Load person 
			URL url = getClass().getResource("/org/juffrou/fx/business/Person.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			Parent parent = loader.load();
			personController = loader.getController();
			personController.bind();
			
			// load BeanLifecycle
			url = getClass().getResource("/org/juffrou/fx/core/BeanLifecycle.fxml");
			loader = new FXMLLoader(url);
			Parent beanLifecycleNode = loader.load();
			AnchorPane pane = (AnchorPane) beanLifecycleNode.lookup("#nodeContainer");
			pane.getChildren().add(parent);
			
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

package org.juffrou.fx;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.business.pm.PersonPM;
import org.juffrou.fx.controller.BeanController;

public class MainController {
	
	@FXML
	private void open() throws IOException {
		System.out.println("open");
		
		Stage stage = new Stage();
		URL url = getClass().getResource("/org/juffrou/fx/business/Person.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		
		Parent parent = loader.load();
		BeanController<Person> controller = loader.getController();
		controller.bind(new PersonPM());
		
		Scene scene = new Scene( parent);
		stage.setScene(scene);
		stage.show();

	}

}

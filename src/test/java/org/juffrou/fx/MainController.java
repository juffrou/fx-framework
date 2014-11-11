package org.juffrou.fx;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.juffrou.fx.business.pm.PersonPM;

public class MainController {
	
	@FXML
	private void open() throws IOException {
		System.out.println("open");
		
		Stage stage = new Stage();
		
		// load BeanLifecycle
//		URL url = getClass().getResource("/org/juffrou/fx/core/BeanLifecycle.fxml");
//		FXMLLoader loader = new FXMLLoader(url);
//		Parent beanLifecycleNode = loader.load();
		
		Scene scene = new Scene( new PersonPM().getNode() );
		stage.setScene(scene);
		stage.show();

	}

}

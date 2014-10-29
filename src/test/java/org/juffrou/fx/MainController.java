package org.juffrou.fx;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private void open() throws IOException {
		System.out.println("open");
		
		Stage stage = new Stage();
		URL url = getClass().getResource("/org/juffrou/fx/business/Person.fxml");
		Parent parent = FXMLLoader.load(url);
		
		Scene scene = new Scene( parent);
		stage.setScene(scene);
		stage.show();

	}

}

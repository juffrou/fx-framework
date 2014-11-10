package org.juffrou.fx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.juffrou.fx.business.pm.PersonPM;

public class MainController {
	
	@FXML
	private void open() throws IOException {
		System.out.println("open");
		
		Stage stage = new Stage();
		Scene scene = new Scene( new PersonPM().getNode() );
		stage.setScene(scene);
		stage.show();

	}

}

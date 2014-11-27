package org.juffrou.fx.controller;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public abstract class AbstractController {

	
	/**
	 * Gets the FXML loader of a particular fxml file and sets the corresponding resource bundle.<p>
	 * Example: <pre>getLoader("/org/juffrou/fx/business/Person");</pre> will load the file /org/juffrou/fx/business/Person.fxml and 
	 * the resources /org/juffrou/fx/business/Person_en_EN.properties (the the default Loacale is en_EN).
	 * @param fxmlPath Path to the fxml file without the extension.
	 * @return the loaded FXML loader
	 */
	public static FXMLLoader getLoader(String fxmlPath) {
		
		if(fxmlPath == null || fxmlPath.isEmpty())
			throw new NullPointerException("The parameter fxmlPath cannot be null or empty.");
		
		URL url = AbstractController.class.getResource(fxmlPath + ".fxml");
		FXMLLoader loader = new FXMLLoader(url);
		Locale locale = Locale.getDefault();
		if(fxmlPath.startsWith("/"))
			fxmlPath = fxmlPath.substring(1);
		fxmlPath.replace('/', '.');
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(fxmlPath, locale);
			loader.setResources(bundle);
		}
		catch(MissingResourceException e) {
		}
		
		return loader;
	}

	
}

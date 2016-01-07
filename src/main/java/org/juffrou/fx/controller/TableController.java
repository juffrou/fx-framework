package org.juffrou.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import org.juffrou.fx.controller.model.ListControllerModel;

/**
 * Controller that supports a node for presenting a List or Set collection.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class TableController<T> implements JFXController {

	ListControllerModel<T> controllerModel;
	
	protected TableController(Class<T> beanClass) {
		controllerModel = new ListControllerModel<>();
		
		controllerModel.getModelSourceProperty().addListener(new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				bindControllerModel(controllerModel);
			}
			
		});
	}

	/**
	 * Called once during controller initialization to bind the scene controls to the data model.
	 * @param presentationModel The data model to bind to.
	 */
	protected abstract void bindControllerModel(ListControllerModel<T> presentationModel);

	/**
	 * Returns the data model of this controller
	 * @return The data model of this controller.
	 */
	public ListControllerModel<T> getControllerModel() {
		return controllerModel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindControllerModel(controllerModel);
	}
}

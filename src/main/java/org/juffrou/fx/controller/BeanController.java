package org.juffrou.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import org.juffrou.fx.controller.model.BeanControllerModel;

/**
 * Controller that supports a node for presenting a Java Bean.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class BeanController<T> implements Initializable {

	private final BeanControllerModel<T> controllerModel;
	
	protected BeanController(Class<T> beanClass) {
		controllerModel = new BeanControllerModel<>(beanClass);
	}

	private void bind() {
		bindControllerModel(controllerModel);
	}

	/**
	 * Bind the scene controls to the bean properties
	 * @param controllerModel Controller Model holding the properties to bin to.
	 */
	protected abstract void bindControllerModel(BeanControllerModel<T> controllerModel);

	public BeanControllerModel<T> getControllerModel() {
		return controllerModel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bind();
	}
	
}

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

	private void unbind() {
	}
	
	private void bind() {
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(BeanControllerModel<T> presentationModel);

	public BeanControllerModel<T> getControllerModel() {
		return controllerModel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bind();
	}
	
}

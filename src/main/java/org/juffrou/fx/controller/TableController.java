package org.juffrou.fx.controller;

import org.juffrou.fx.controller.model.TableControllerModel;

/**
 * Controller that supports a node for presenting a List or Set collection.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class TableController<T> {

	TableControllerModel<T> controllerModel;
	
	protected TableController(Class<T> beanClass) {
		controllerModel = new TableControllerModel<>();
	}

	protected void unbind() {
	}
	
	public void bind(TableControllerModel<T> controllerModel) {
		this.controllerModel = controllerModel;
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(TableControllerModel<T> presentationModel);

	protected TableControllerModel<T> getControllerModel() {
		return controllerModel;
	}
}

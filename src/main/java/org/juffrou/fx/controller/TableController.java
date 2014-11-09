package org.juffrou.fx.controller;

import org.juffrou.fx.presentationmodel.ControllerModel;

/**
 * Controller that supports a node for presenting a Java Bean.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class TableController<T> {

	ControllerModel<T> controllerModel;

	protected void unbind() {
	}
	
	public void bind(ControllerModel<T> controllerModel) {
		this.controllerModel = controllerModel;
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(ControllerModel<T> presentationModel);

	protected ControllerModel<T> getControllerModel() {
		return controllerModel;
	}
}

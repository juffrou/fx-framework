package org.juffrou.fx.controller;

import org.juffrou.fx.controller.model.BeanControllerModel;

/**
 * Controller that supports a node for presenting a Java Bean.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class TreeController<T> {

	BeanControllerModel<T> controllerModel;

	protected void unbind() {
	}
	
	public void bind(BeanControllerModel<T> controllerModel) {
		this.controllerModel = controllerModel;
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(BeanControllerModel<T> presentationModel);

	protected BeanControllerModel<T> getControllerModel() {
		return controllerModel;
	}
}

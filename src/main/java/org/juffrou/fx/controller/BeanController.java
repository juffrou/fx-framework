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
public abstract class BeanController<T> {

	private final ControllerModel<T> controllerModel;
	
	protected BeanController(Class<T> beanClass) {
		controllerModel = new ControllerModel<>(beanClass);
	}

	protected void unbind() {
	}
	
	public void bind() {
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(ControllerModel<T> presentationModel);

	public ControllerModel<T> getControllerModel() {
		return controllerModel;
	}
	
}

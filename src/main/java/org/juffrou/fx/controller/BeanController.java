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
public abstract class BeanController<T> {

	private final BeanControllerModel<T> controllerModel;
	
	protected BeanController(Class<T> beanClass) {
		controllerModel = new BeanControllerModel<>(beanClass);
	}

	protected void unbind() {
	}
	
	public void bind() {
		bindControllerModel(controllerModel);
	}

	protected abstract void bindControllerModel(BeanControllerModel<T> presentationModel);

	public BeanControllerModel<T> getControllerModel() {
		return controllerModel;
	}
	
}

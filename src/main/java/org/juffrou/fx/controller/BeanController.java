package org.juffrou.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.juffrou.fx.controller.bind.Binder;
import org.juffrou.fx.controller.model.BeanControllerModel;
import org.juffrou.fx.controller.model.JFXModel;
import org.juffrou.fx.serials.JFXProxy;

/**
 * Controller that supports a node for presenting a Java Bean.<p>
 * Binds its controls to a ControllerModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class BeanController<T> implements JFXController {

	private final BeanControllerModel<T> controllerModel;
	private final Binder binder;

	
	protected BeanController(Class<T> beanClass) {
		this.controllerModel = new BeanControllerModel<>();
		this.binder = new Binder((JFXModel<? extends JFXProxy>) controllerModel);
	}

	/**
	 * Bind the scene controls to the bean properties
	 * @param controllerModel Controller Model holding the properties to bind to.
	 */
	protected abstract void bindControllerModel(Binder binder);

	public BeanControllerModel<T> getControllerModel() {
		return controllerModel;
	}
	
	public Binder getBinder() {
		return binder;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindControllerModel(binder);
	}
}

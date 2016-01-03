package org.juffrou.fx.controller.model;

import org.juffrou.fx.serials.JFXProxy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Wraps a traditional java bean and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class BeanControllerModel<T> implements JFXModel<T> {

	private final ObjectProperty<T> fxProxyHolder;
	
	public BeanControllerModel() {
		this.fxProxyHolder = new SimpleObjectProperty<>();
	}
	
	public void addListener(ChangeListener<T> changeListener) {
		fxProxyHolder.addListener(changeListener);
	}
	
	public Property<T> getModelSourceProperty() {
		return fxProxyHolder;
	}
	
	public void setModelSource(T fxProxy) {
		this.fxProxyHolder.set(fxProxy);
	}
	
	public T getModelSource() {
		return fxProxyHolder.get();
	}
	
	private ReadOnlyProperty<?> getBeanProperty(String propertyName) {
		JFXProxy fxProxy = (JFXProxy) fxProxyHolder.get();
		if(fxProxy == null)
			return null;
		return fxProxy.getProperty(propertyName);
	}
	
	/**
	 * Bind a controller model to a property of the traditional java bean in this Bean Controller Model.<br>
	 * The controller model will listen to changes on the property.
	 * @param controllerModel A Table Controller Model
	 * @param propertyName Name of a property in this traditional java bean corresponding to a field of type collection
	 */
	/*
	public <PT> void controllerModelBind(TableControllerModel<PT> controllerModel, String propertyName) {
		ReadOnlyProperty<Collection<PT>> beanProperty = (ReadOnlyProperty<Collection<PT>>) getProperty(propertyName);
		beanProperty.addListener(controllerModel);
		boundProperties.put(propertyName, beanProperty);
	}
	*/
	
	/**
	 * Bind a controller model to a property of the traditional java bean in this Bean Controller Model.<br>
	 * The controller model will listen to changes on the property.
	 * @param controllerModel A bean Controller Model
	 * @param propertyName Name of a property in this traditional java bean corresponding to a field implementing FxSerials
	 */
	/*
	public <PT> void controllerModelBind(BeanControllerModel<PT> controllerModel, String propertyName) {
		ReadOnlyProperty<PT> beanProperty = (ReadOnlyProperty<PT>) getProperty(propertyName);
		beanProperty.addListener(controllerModel);
		boundProperties.put(propertyName, beanProperty);
	}
	*/
	
	/**
	 * Create a unidirection binding for the property passed.
	 * @param property Property to be bound
	 * @param propertyName The name of the observable bean property to which the property passed should be bound to
	 */
	public <PT> void bind(Property<PT> property, String propertyName) {
		ReadOnlyProperty<?> beanProperty = getBeanProperty(propertyName);
		property.bind((ObservableValue<? extends PT>) beanProperty);
	}

	/**
	 * Create a bidirectional binding between this Property and another one. 
	 * Bidirectional bindings exists independently of unidirectional bindings. 
	 * So it is possible to add unidirectional binding to a property with bidirectional binding and vice-versa. However, this practice is discouraged.
	 * It is possible to have multiple bidirectional bindings of one Property.
	 * @param property
	 * @param propertyName
	 */
	public <PT> void bindBidirectional(Property<PT> property, String propertyName) {
		ReadOnlyProperty<?> beanProperty = getBeanProperty(propertyName);
		property.bindBidirectional((Property<PT>) beanProperty);
	}
	
	public <PT> void bindBidirectional(JFXModel<PT> model, String propertyName) {
		ReadOnlyProperty<?> beanProperty = getBeanProperty(propertyName);
		model.getModelSourceProperty().bindBidirectional((Property<PT>) beanProperty);
	}

}

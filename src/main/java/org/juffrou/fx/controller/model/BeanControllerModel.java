package org.juffrou.fx.controller.model;

import java.util.Deque;
import java.util.Map;

import org.juffrou.fx.controller.bind.Binder;
import org.juffrou.fx.serials.JFXProxy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Model backed by an ObjectProperty holding a JFXProxy bean. This model allows a JavaFX controller to bind its controls with the properties of the bean.<br>
 * Each property of the JFXProxy can itself be bound to a property that backs up another JFXModel.
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
		if( !JFXProxy.class.isAssignableFrom(fxProxy.getClass()) )
			throw new IllegalArgumentException("Parameter must be a JFXProxy. Please use fxSerialsContext.getProxy(...) before.");
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
	
}

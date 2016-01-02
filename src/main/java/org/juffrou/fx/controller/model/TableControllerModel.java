package org.juffrou.fx.controller.model;

import java.util.Collection;

import org.juffrou.fx.serials.FxSerialsContext;
import org.juffrou.fx.serials.JFXProxy;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps a traditional java collection and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class TableControllerModel<T> implements ChangeListener<Collection<T>> {
	

	private final FxSerialsContext serialsUtil;
	private SimpleListProperty<T> simpleListProperty;
	
	public TableControllerModel() {
		serialsUtil = new FxSerialsContext();
	}
	
	public void setModelSource(SimpleListProperty simpleListProperty) {
		this.simpleListProperty = simpleListProperty;
	}
	
	public ObservableList<T> getModelSource() {
		return simpleListProperty;
	}

	@Override
	public void changed(ObservableValue<? extends Collection<T>> observable, Collection<T> oldValue,
			Collection<T> newValue) {
		
		simpleListProperty.replaceAll(operator);
	}
	
}

package org.juffrou.fx.controller.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

/**
 * Wraps a traditional java collection and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class ListControllerModel<T> implements JFXModel<T> {
	

	private final SimpleListProperty<T> simpleListProperty;
	
	public ListControllerModel() {
		this.simpleListProperty = new SimpleListProperty<>();
	}
	
	public Property<T> getModelSourceProperty() {
		return (Property<T>) simpleListProperty;
	}

	public void setModelSource(ObservableList<T> observableList) {
		this.simpleListProperty.set(observableList);
	}
	
	public ObservableList<T> getModelSource() {
		return simpleListProperty.get();
	}

}

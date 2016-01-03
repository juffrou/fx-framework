package org.juffrou.fx.controller.model;

import javafx.beans.property.Property;

/**
 * Represents a controller model backed by a property. This property will be an ObjectProperty holding a JFXProxy in case of a BeanControllerModel or a SimpleListProperty in case of a ListControllerModel.
 * 
 * @author cemartins
 *
 * @param <T>
 */
public interface JFXModel<T> {

	/**
	 * Obtain the property that backs the model
	 * @return
	 */
	Property<T> getModelSourceProperty();
}

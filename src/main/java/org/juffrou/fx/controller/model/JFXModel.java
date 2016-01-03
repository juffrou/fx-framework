package org.juffrou.fx.controller.model;

import javafx.beans.property.Property;

public interface JFXModel<T> {

	Property<T> getModelSourceProperty();
}

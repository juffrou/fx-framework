package org.juffrou.fx.controller.bind;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.juffrou.fx.controller.model.JFXModel;
import org.juffrou.fx.serials.JFXProxy;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Holds information about the properties to bind to the JFXProxy bean.<br>
 * Performs the unbinding and the binding every time the Model's property changes.
 * @author cemartins
 *
 * @param <T>
 */
public class Binder implements ChangeListener<JFXProxy> {

	private final Map<String, Property<?>> bidirectionalBindings;
	private final Map<String, Property<?>> bindings;

	public Binder(JFXModel<? extends JFXProxy> jfxModel) {
		jfxModel.getModelSourceProperty().addListener(this);
		this.bidirectionalBindings = new HashMap<>();
		this.bindings = new HashMap<>();
	}

	/**
	 * Create a unidirectional binding between the Property passed and a property of the java bean.
	 * @param property Property to bind.
	 * @param propertyName The name of the bean property to bind to.
	 */
	public void add(Property<?> property, String propertyName) {
		bindings.put(propertyName, property);
	}
	
	/**
	 * Create a bidirectional binding between the Property passed and a property of the java bean.<p>
	 * Bidirectional bindings exists independently of unidirectional bindings. 
	 * So it is possible to add unidirectional binding to a property with bidirectional binding and vice-versa. However, this practice is discouraged.<br>
	 * It is possible to have multiple bidirectional bindings of one Property.
	 * @param property The property to bind.
	 * @param propertyName The name of the bean property to bind to.
	 */
	public void addBidirectional(Property<?> property, String propertyName) {
		bidirectionalBindings.put(propertyName, property);
	}

	/**
	 * Creates a bidirectional binding between the property that backs the JFXModel and a property of the java bean.
	 * @param model Controller model backed by a property
	 * @param propertyName The name of the bean property to bind to.
	 */
	public void addBidirectional(JFXModel<?> model, String propertyName) {
		bidirectionalBindings.put(propertyName, model.getModelSourceProperty());
	}

	/**
	 * Create a unidirectional binding between the Property passed and a property of the java bean.
	 * @param property Property to bind.
	 * @param propertyName The name of the bean property to bind to.
	 */
	private <PT> void bind(Property<PT> property, JFXProxy jfxProxy, String propertyName) {
		ReadOnlyProperty<?> beanProperty = jfxProxy.getProperty(propertyName);
		property.bind((ObservableValue<? extends PT>) beanProperty);
	}
	
	private <PT> void unbind(Property<PT> property, JFXProxy jfxProxy, String propertyName) {
		property.unbind();
	}

	/**
	 * Create a bidirectional binding between the Property passed and a property of the java bean.<p>
	 * Bidirectional bindings exists independently of unidirectional bindings. 
	 * So it is possible to add unidirectional binding to a property with bidirectional binding and vice-versa. However, this practice is discouraged.<br>
	 * It is possible to have multiple bidirectional bindings of one Property.
	 * @param property The property to bind.
	 * @param propertyName The name of the bean property to bind to.
	 */
	private <PT> void bindBidirectional(Property<PT> property, JFXProxy jfxProxy, String propertyName) {
		ReadOnlyProperty<?> beanProperty = jfxProxy.getProperty(propertyName);
		property.bindBidirectional((Property<PT>) beanProperty);
	}

	private <PT> void unbindBidirectional(Property<PT> property, JFXProxy jfxProxy, String propertyName) {
		ReadOnlyProperty<?> beanProperty = jfxProxy.getProperty(propertyName);
		property.unbindBidirectional((Property<PT>) beanProperty);
	}

	/* Called every time the model's backing property changes.
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends JFXProxy> observable, JFXProxy oldValue, JFXProxy newValue) {

		for(Entry<String, Property<?>> entry : bindings.entrySet()) {
			if(oldValue != null)
				unbind(entry.getValue(), oldValue, entry.getKey());
			if(newValue != null)
				bind(entry.getValue(), newValue, entry.getKey());
		}

		for(Entry<String, Property<?>> entry : bidirectionalBindings.entrySet()) {
			if(oldValue != null)
				unbindBidirectional(entry.getValue(), oldValue, entry.getKey());
			if(newValue != null)
				bindBidirectional(entry.getValue(), newValue, entry.getKey());
		}
}

}

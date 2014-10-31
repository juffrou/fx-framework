package org.juffrou.fx.reflect;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.JuffrouBeanWrapper;
import net.sf.juffrou.reflect.internal.BeanFieldHandler;

/**
 * BeanWrapper than stores a JavaFX property per bean property
 * 
 * @author Carlos Martins
 */
public class FXBeanWrapper extends JuffrouBeanWrapper {
	
	private Map<String, ReadOnlyJavaBeanProperty<?>> beanProperties = new HashMap<String, ReadOnlyJavaBeanProperty<?>>();

	public FXBeanWrapper(BeanWrapperContext context,
			JuffrouBeanWrapper parentBeanWrapper, String parentBeanProperty) {
		super(context, parentBeanWrapper, parentBeanProperty);
	}

	public FXBeanWrapper(BeanWrapperContext context, Object instance) {
		super(context, instance);
	}

	public FXBeanWrapper(BeanWrapperContext context) {
		super(context);
	}

	public FXBeanWrapper(Class<?> clazz) {
		super(clazz);
	}

	public FXBeanWrapper(Object instance) {
		super(instance);
	}
	
	public void setBeanProperty(String propertyName, ReadOnlyJavaBeanProperty<?> property) {
		beanProperties.put(propertyName, property);
	}
	
	public ReadOnlyJavaBeanProperty<?> getBeanProperty(String propertyName) {
		return beanProperties.get(propertyName);
	}
	
	@Override
	public void setValue(String propertyName, Object value) {
		super.setValue(propertyName, value);
		ReadOnlyJavaBeanProperty<?> beanProperty = beanProperties.get(propertyName);
		if(beanProperty != null)
			beanProperty.fireValueChangedEvent();
	}
	
	@Override
	public void setValueOfString(String propertyName, String value) {
		super.setValueOfString(propertyName, value);
		ReadOnlyJavaBeanProperty<?> beanProperty = beanProperties.get(propertyName);
		if(beanProperty != null)
			beanProperty.fireValueChangedEvent();
	}
	
	public boolean isReadonly(String propertyName) {
		BeanFieldHandler beanFieldHandler = getContext().getBeanFieldHandler(propertyName);
		//TODO improve beanwrapper
		return false;
	}

}

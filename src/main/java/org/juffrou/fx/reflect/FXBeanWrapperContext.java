package org.juffrou.fx.reflect;

import java.lang.reflect.Type;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.CustomizableBeanWrapperFactory;
import net.sf.juffrou.reflect.ReflectionUtil;

public class FXBeanWrapperContext extends BeanWrapperContext {
	
	private Map<String, ObservableValue<?>> properties;

	public FXBeanWrapperContext(CustomizableBeanWrapperFactory factory, Class clazz, Type... types) {
		super(factory, clazz, types);
	}

	public Property<?> getProperty(String propertyName) {
		ObservableValue<?> property = properties.get(propertyName);
		if(property == null) {
			Class<?> clazz = ReflectionUtil.getClass(getType(propertyName));
			
		}
		return (Property<?>) property;
	}
}

package org.juffrou.fx.reflect;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanObjectPropertyBuilder;
import javafx.beans.value.ObservableValue;

import org.juffrou.fx.error.PropertyCreationException;

public class FXPropertyFactory implements PropertyFactory {

	public ObservableValue<?> getProperty(Class<?> beanClass, String propertyName, Class<?> propertyClass, boolean isReadonly) {
		
		try {
			if(propertyClass == String.class)
				return new SimpleStringProperty();
			else
				return isReadonly ? ReadOnlyJavaBeanObjectPropertyBuilder.create().beanClass(beanClass).name(propertyName).build() : JavaBeanObjectPropertyBuilder.create().beanClass(beanClass).name(propertyName).build();
					
			
		} catch (NoSuchMethodException e) {
			throw new PropertyCreationException(e);
		}
		
	}

}

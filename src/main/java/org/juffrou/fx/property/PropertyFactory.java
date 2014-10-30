package org.juffrou.fx.property;

import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanStringPropertyBuilder;
import javafx.beans.value.ObservableValue;

import org.juffrou.fx.error.PropertyCreationException;

public class PropertyFactory {

	public ObservableValue<?> getProperty(Object bean, String propertyName, Class<?> propertyType, boolean isReadonly) {
		
		try {
			if(propertyType == String.class)
				return isReadonly ? ReadOnlyJavaBeanStringPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanStringPropertyBuilder.create().bean(bean).name(propertyName).build();
			else
				return isReadonly ? ReadOnlyJavaBeanObjectPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanObjectPropertyBuilder.create().bean(bean).name(propertyName).build();
					
			
		} catch (NoSuchMethodException e) {
			throw new PropertyCreationException(e);
		}
		
	}
}

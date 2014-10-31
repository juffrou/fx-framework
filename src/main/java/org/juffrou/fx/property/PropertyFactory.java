package org.juffrou.fx.property;

import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.JavaBeanFloatPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanLongPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanFloatPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanIntegerPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanLongPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanStringPropertyBuilder;

import org.juffrou.fx.error.PropertyCreationException;

public class PropertyFactory {

	public ReadOnlyJavaBeanProperty<?> getProperty(Object bean, String propertyName, Class<?> propertyType, boolean isReadonly) {

		try {
			if (propertyType == String.class)
				return isReadonly ? ReadOnlyJavaBeanStringPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanStringPropertyBuilder.create().bean(bean).name(propertyName)
						.build();
			else if (propertyType == Integer.class)
				return isReadonly ? ReadOnlyJavaBeanIntegerPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanIntegerPropertyBuilder.create().bean(bean).name(propertyName)
						.build();
			else if (propertyType == Long.class)
				return isReadonly ? ReadOnlyJavaBeanLongPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanLongPropertyBuilder.create().bean(bean).name(propertyName).build();
			else if (propertyType == Boolean.class)
				return isReadonly ? ReadOnlyJavaBeanBooleanPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanBooleanPropertyBuilder.create().bean(bean).name(propertyName)
						.build();
			else if (propertyType == Double.class)
				return isReadonly ? ReadOnlyJavaBeanDoublePropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanDoublePropertyBuilder.create().bean(bean).name(propertyName).build();
			else if (propertyType == Float.class)
				return isReadonly ? ReadOnlyJavaBeanFloatPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanFloatPropertyBuilder.create().bean(bean).name(propertyName).build();
			else
				return isReadonly ? ReadOnlyJavaBeanObjectPropertyBuilder.create().bean(bean).name(propertyName).build() : JavaBeanObjectPropertyBuilder.create().bean(bean).name(propertyName).build();

		} catch (NoSuchMethodException e) {
			throw new PropertyCreationException(e);
		}

	}
}

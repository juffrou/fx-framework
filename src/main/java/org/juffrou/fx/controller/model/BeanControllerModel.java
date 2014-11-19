package org.juffrou.fx.controller.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import javafx.beans.value.ObservableValue;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.JuffrouBeanWrapper;

import org.juffrou.fx.serials.FxSerialsProxy;
import org.juffrou.fx.serials.FxSerialsUtil;

/**
 * Wraps a traditional java bean and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class BeanControllerModel<T> {

	private final JuffrouBeanWrapper modelSourceBeanWrapper;
	private final T fxSerialsProxy;
	private final BeanWrapperContext beanWrapperContext;
	private final Map<String, ReadOnlyJavaBeanProperty<?>> boundProperties;
	
	public BeanControllerModel(Class<T> backingDomainClass) {
		beanWrapperContext = BeanWrapperContext.create(backingDomainClass);
		
		modelSourceBeanWrapper = new JuffrouBeanWrapper(beanWrapperContext);
		
		FxSerialsUtil serialsUtil = new FxSerialsUtil();
		fxSerialsProxy = serialsUtil.getProxy(backingDomainClass);
		
		boundProperties = new HashMap<String, ReadOnlyJavaBeanProperty<?>>();
	}
	
	private ReadOnlyJavaBeanProperty<?> getProperty(String propertyName) {
		return ((FxSerialsProxy)fxSerialsProxy).getProperty(propertyName);
	}
	
	//TODO maybe the model classes should implement ObservableValue / Property
	public ReadOnlyProperty<Collection<?>> getCollectionProperty(String propertyName) {
		ReadOnlyJavaBeanProperty<Collection<?>> beanProperty = (ReadOnlyJavaBeanProperty<Collection<?>>) getProperty(propertyName);
		boundProperties.put(propertyName, beanProperty);
		return beanProperty;
	}
	
	public <PT> void bindReadonly(Property<PT> property, String propertyName) {
		ReadOnlyJavaBeanProperty<?> beanProperty = getProperty(propertyName);
		property.bind((ObservableValue<? extends PT>) beanProperty);
		boundProperties.put(propertyName, beanProperty);
	}

	public <PT> void bindReadWrite(Property<PT> property, String propertyName) {
		ReadOnlyJavaBeanProperty<?> beanProperty = getProperty(propertyName);
		property.bindBidirectional((Property<PT>) beanProperty);
		boundProperties.put(propertyName, beanProperty);
	}


	public void setModelSource(T backingDomain) {
		Class<?> beanClass = beanWrapperContext.getBeanClass();
		if( ! beanClass.isAssignableFrom(backingDomain.getClass()) )
			throw new IllegalArgumentException("backing domain is not of type " + beanClass.getSimpleName());
		
		modelSourceBeanWrapper.setBean(backingDomain);
		for (String propertyName : boundProperties.keySet()) {
//			Class<?> propertyClass = ReflectionUtil.getClass(beanWrapperContext.getType(propertyName));
			Object value = modelSourceBeanWrapper.getValue(propertyName);
			/*
			if(List.class.isAssignableFrom(propertyClass) && ! Observable.class.isAssignableFrom(propertyClass))
				value = FXCollections.observableArrayList(value);
			else
				if(Set.class.isAssignableFrom(propertyClass) && ! Observable.class.isAssignableFrom(propertyClass))
					value = FXCollections.observableSet(value);
				else
					if(Map.class.isAssignableFrom(propertyClass) && ! Observable.class.isAssignableFrom(propertyClass))
						value = FXCollections.observableMap((Map<?, ?>) value);
			*/
			ReadOnlyJavaBeanProperty<?> roProperty = boundProperties.get(propertyName);
			if(JavaBeanProperty.class.isAssignableFrom(roProperty.getClass()))
				((JavaBeanProperty)roProperty).setValue(value);
		}
	}
	
	public T getModelSource() {
		JuffrouBeanWrapper fxBeanWrapper = new JuffrouBeanWrapper(beanWrapperContext, fxSerialsProxy);
		for (String propertyName : beanWrapperContext.getFields().keySet())
			modelSourceBeanWrapper.setValue(propertyName, fxBeanWrapper.getValue(propertyName));
		return (T) modelSourceBeanWrapper.getBean(false);
	}
	
	public Class<T> getModelSourceClass() {
		return (Class<T>) beanWrapperContext.getBeanClass();
	}
	
}

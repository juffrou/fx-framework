package org.juffrou.fx.presentationmodel;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.JuffrouBeanWrapper;

import org.juffrou.fx.property.PropertyFactory;

public class BasePresentationModel<T> {

	private Map<String, ObservableValue<?>> properties;
	private BeanWrapperContext beanWrapperContext;
	private JuffrouBeanWrapper beanWrapper;
	
	public BasePresentationModel(Class<T> backingDomainClass) {
		properties = new HashMap<String, ObservableValue<?>>();
		beanWrapperContext = BeanWrapperContext.create(backingDomainClass);
		beanWrapper = new JuffrouBeanWrapper(beanWrapperContext);
	}
	
	private ObservableValue<?> getProperty(String propertyName) {
		
		ObservableValue<?> property = properties.get(propertyName);
		if(property == null) {
			PropertyFactory propertyFactory = new PropertyFactory();
			property = propertyFactory.getProperty(beanWrapper.getBean(), propertyName, beanWrapper.getClazz(propertyName), false);
			properties.put(propertyName, property);
		}
		return property;
	}
	
	public <PT> void bindReadonly(Property<PT> property, String propertyName) {
		property.bind((ObservableValue<? extends PT>) getProperty(propertyName));
	}

	public <PT> void bindReadWrite(Property<PT> property, String propertyName) {
		property.bindBidirectional((Property<PT>) getProperty(propertyName));
	}


	public void setNewPresentationModelDomain(T backingDomain) {
		Class<?> beanClass = beanWrapper.getBeanClass();
		if( ! beanClass.isAssignableFrom(backingDomain.getClass()) )
			throw new IllegalArgumentException("backing domain is not of type " + beanClass.getSimpleName());
		
		JuffrouBeanWrapper backingWrapper = new JuffrouBeanWrapper(beanWrapperContext, backingDomain);
		for (String propertyName : beanWrapper.getPropertyNames()) {
			ObservableValue<?> property = properties.get(propertyName);
			if(property != null && property instanceof Property)
				((Property)property).setValue(backingWrapper.getValue(propertyName));
			else
				beanWrapper.setValue(propertyName, backingWrapper.getValue(propertyName));
		}
	}
	
	public T getNewPresentationModelDomain() {
		JuffrouBeanWrapper backingWrapper = new JuffrouBeanWrapper(beanWrapperContext);
		for (String propertyName : beanWrapper.getPropertyNames()) {
			backingWrapper.setValue(propertyName, beanWrapper.getValue(propertyName));
		}
		return (T) backingWrapper.getBean();
	}
}

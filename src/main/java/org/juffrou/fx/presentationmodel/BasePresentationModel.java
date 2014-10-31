package org.juffrou.fx.presentationmodel;

import javafx.beans.property.Property;
import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import javafx.beans.value.ObservableValue;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.JuffrouBeanWrapper;

import org.juffrou.fx.property.PropertyFactory;
import org.juffrou.fx.reflect.FXBeanWrapper;

/**
 * Wraps a traditional java bean and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class BasePresentationModel<T> {

	private final BeanWrapperContext beanWrapperContext;
	private final FXBeanWrapper fxBeanWrapper;
	
	public BasePresentationModel(Class<T> backingDomainClass) {
		beanWrapperContext = BeanWrapperContext.create(backingDomainClass);
		fxBeanWrapper = new FXBeanWrapper(beanWrapperContext);
	}
	
	private ReadOnlyJavaBeanProperty<?> getProperty(String propertyName) {
		
		ReadOnlyJavaBeanProperty<?> property = fxBeanWrapper.getBeanProperty(propertyName);
		if(property == null) {
			PropertyFactory propertyFactory = new PropertyFactory();
			property = propertyFactory.getProperty(fxBeanWrapper.getBean(), propertyName, fxBeanWrapper.getClazz(propertyName), false);
			fxBeanWrapper.setBeanProperty(propertyName, property);
		}
		return property;
	}
	
	public <PT> void bindReadonly(Property<PT> property, String propertyName) {
		property.bind((ObservableValue<? extends PT>) getProperty(propertyName));
	}

	public <PT> void bindReadWrite(Property<PT> property, String propertyName) {
		property.bindBidirectional((Property<PT>) getProperty(propertyName));
	}


	public void setModelDomainInstance(T backingDomain) {
		Class<?> beanClass = fxBeanWrapper.getBeanClass();
		if( ! beanClass.isAssignableFrom(backingDomain.getClass()) )
			throw new IllegalArgumentException("backing domain is not of type " + beanClass.getSimpleName());
		
		JuffrouBeanWrapper backingWrapper = new JuffrouBeanWrapper(beanWrapperContext, backingDomain);
		for (String propertyName : fxBeanWrapper.getPropertyNames())
			fxBeanWrapper.setValue(propertyName, backingWrapper.getValue(propertyName));
	}
	
	public T getModelDomainInstance() {
		JuffrouBeanWrapper backingWrapper = new JuffrouBeanWrapper(beanWrapperContext);
		for (String propertyName : fxBeanWrapper.getPropertyNames()) {
			backingWrapper.setValue(propertyName, fxBeanWrapper.getValue(propertyName));
		}
		return (T) backingWrapper.getBean();
	}
	
	public FXBeanWrapper getModelDomainWrapper() {
		return fxBeanWrapper;
	}
}

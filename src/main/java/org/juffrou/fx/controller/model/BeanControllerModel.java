package org.juffrou.fx.controller.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import javafx.beans.value.ChangeListener;
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
public class BeanControllerModel<T> implements ChangeListener<T> {

	private final FxSerialsUtil serialsUtil;
	private final JuffrouBeanWrapper modelSourceBeanWrapper;
	private final T fxSerialsProxy;
	private final BeanWrapperContext beanWrapperContext;
	private final Map<String, ReadOnlyJavaBeanProperty<?>> boundProperties;
	
	public BeanControllerModel(Class<T> backingDomainClass) {
		beanWrapperContext = BeanWrapperContext.create(backingDomainClass);
		
		modelSourceBeanWrapper = new JuffrouBeanWrapper(beanWrapperContext);
		
		serialsUtil = new FxSerialsUtil();
		fxSerialsProxy = serialsUtil.getProxy(backingDomainClass);
		
		boundProperties = new HashMap<String, ReadOnlyJavaBeanProperty<?>>();
	}
	
	private ReadOnlyJavaBeanProperty<?> getProperty(String propertyName) {
		return ((FxSerialsProxy)fxSerialsProxy).getProperty(propertyName);
	}
	
	/**
	 * Bind a controller model to a property of the traditional java bean in this Bean Controller Model.<br>
	 * The controller model will listen to changes on the property.
	 * @param controllerModel A Table Controller Model
	 * @param propertyName Name of a property in this traditional java bean corresponding to a field of type collection
	 */
	public <PT> void controllerModelBind(TableControllerModel<PT> controllerModel, String propertyName) {
		ReadOnlyJavaBeanProperty<Collection<PT>> beanProperty = (ReadOnlyJavaBeanProperty<Collection<PT>>) getProperty(propertyName);
		beanProperty.addListener(controllerModel);
		boundProperties.put(propertyName, beanProperty);
	}
	
	/**
	 * Bind a controller model to a property of the traditional java bean in this Bean Controller Model.<br>
	 * The controller model will listen to changes on the property.
	 * @param controllerModel A bean Controller Model
	 * @param propertyName Name of a property in this traditional java bean corresponding to a field implementing FxSerials
	 */
	public <PT> void controllerModelBind(BeanControllerModel<PT> controllerModel, String propertyName) {
		ReadOnlyJavaBeanProperty<PT> beanProperty = (ReadOnlyJavaBeanProperty<PT>) getProperty(propertyName);
		beanProperty.addListener(controllerModel);
		boundProperties.put(propertyName, beanProperty);
	}
	
	/**
	 * Create a unidirection binding for the property passed.
	 * @param property Property to be bound
	 * @param propertyName The name of the observable bean property to which the property passed should be bound to
	 */
	public <PT> void readonlyBind(Property<PT> property, String propertyName) {
		ReadOnlyJavaBeanProperty<?> beanProperty = getProperty(propertyName);
		property.bind((ObservableValue<? extends PT>) beanProperty);
		boundProperties.put(propertyName, beanProperty);
	}

	/**
	 * Create a bidirectional binding between this Property and another one. 
	 * Bidirectional bindings exists independently of unidirectional bindings. 
	 * So it is possible to add unidirectional binding to a property with bidirectional binding and vice-versa. However, this practice is discouraged.
	 * It is possible to have multiple bidirectional bindings of one Property.
	 * @param property
	 * @param propertyName
	 */
	public <PT> void readWriteBind(Property<PT> property, String propertyName) {
		ReadOnlyJavaBeanProperty<?> beanProperty = getProperty(propertyName);
		property.bindBidirectional((Property<PT>) beanProperty);
		boundProperties.put(propertyName, beanProperty);
	}


	public void setModelSource(T backingDomain) {
		Class<?> beanClass = beanWrapperContext.getBeanClass();
		if( ! beanClass.isAssignableFrom(backingDomain.getClass()) )
			throw new IllegalArgumentException("backing domain is not of type " + beanClass.getSimpleName());

		if( ! FxSerialsProxy.class.isAssignableFrom(backingDomain.getClass()) ) {
			// convert the elements of the collection into JavaFX Beans
			backingDomain = serialsUtil.getProxy(backingDomain);
		}

		
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

	@Override
	public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
		setModelSource(newValue);
	}
	
}

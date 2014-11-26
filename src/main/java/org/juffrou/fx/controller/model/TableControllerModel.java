package org.juffrou.fx.controller.model;

import java.util.Collection;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.juffrou.fx.serials.FxSerialsProxy;
import org.juffrou.fx.serials.FxSerialsUtil;

/**
 * Wraps a traditional java bean and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class TableControllerModel<T> {
	

	private final ObservableList<T> observableArrayList;
	private final FxSerialsUtil serialsUtil;
	private final SourceCollectionChangeListener sourceCollectionChangeListener;
	
	public TableControllerModel() {
		
		observableArrayList = FXCollections.observableArrayList();
		
		serialsUtil = new FxSerialsUtil();
		
		sourceCollectionChangeListener = new SourceCollectionChangeListener();
	}
	

	public void bindModel(ReadOnlyProperty<? extends Collection<T>> sourceProperty) {
		sourceProperty.addListener(sourceCollectionChangeListener);
	}

	public void setModelSource(Collection<T> backingCollection) {
		
		observableArrayList.clear();
		
		if( ! backingCollection.isEmpty()) {
			
			T element = backingCollection.iterator().next();
			
			if( ! FxSerialsProxy.class.isAssignableFrom(element.getClass()) ) {
				// convert the elements of the collection into JavaFX Beans
				backingCollection = serialsUtil.getProxy(backingCollection);
			}
			else {
				ReadOnlyJavaBeanProperty property = ((FxSerialsProxy) element).getProperty("description");
				System.out.println("Property= " + property);
			}
			
			observableArrayList.addAll(backingCollection);
			
		}
	}
	
	public ObservableList<T> getModelSource() {
		return observableArrayList;
	}
	
	
	private class SourceCollectionChangeListener implements ChangeListener<Collection<T>> {

		@Override
		public void changed(ObservableValue<? extends Collection<T>> observable, Collection<T> oldValue, Collection<T> newValue) {
			setModelSource(newValue);
		}
		
	}
	
}

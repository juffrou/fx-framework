package org.juffrou.fx.controller.model;

import java.util.Collection;

import org.juffrou.fx.serials.FxSerialsUtil;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps a traditional java bean and creates JavaFX properties allowing a JavaFX controller to bind its controls with the properties of the bean.
 * 
 * @author Carlos Martins
 * @param <T> java bean type supporting this controller 
 */
public class TableControllerModel<T> {
	

	private final ObservableList<T> observableArrayList;
	private final FxSerialsUtil serialsUtil;
	
	public TableControllerModel() {
		
		observableArrayList = FXCollections.observableArrayList();
		
		serialsUtil = new FxSerialsUtil();
	}
	

	public void bindModel(ReadOnlyProperty<? extends Collection<T>> sourceProperty) {
		sourceProperty.addListener(new SourceCollectionChangeListener());
	}

	public void setModelSource(Collection<T> backingCollection) {
		
		// convert the elements of the collection into JavaFX Beans
		Collection<T> fxCollection = serialsUtil.getProxy(backingCollection);
		observableArrayList.clear();
		observableArrayList.addAll(fxCollection);
	}
	
	public ObservableList<T> getModelSource() {
		return observableArrayList;
	}
	
	
	private class SourceCollectionChangeListener implements ChangeListener<Collection<T>> {

		@Override
		public void changed(ObservableValue<? extends Collection<T>> observable, Collection<T> oldValue, Collection<T> newValue) {
			Collection<T> proxy = serialsUtil.getProxy(newValue);
			setModelSource(proxy);
		}
		
	}
	
}

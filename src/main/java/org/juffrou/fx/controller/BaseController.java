package org.juffrou.fx.controller;

import org.juffrou.fx.presentationmodel.BasePresentationModel;

/**
 * Controller that binds its controls to a BasePresentationModel
 * 
 * @author Carlos Martins
 *
 * @param <T> java bean type supporting this controller
 */
public abstract class BaseController<T> {

	BasePresentationModel<T> basePresentationModel;

	protected void unbind() {
	}
	
	public void bind(BasePresentationModel<T> presentationModel) {
		this.basePresentationModel = presentationModel;
		bindPresentationModel(presentationModel);
	}

	protected abstract void bindPresentationModel(BasePresentationModel<T> presentationModel);

	protected BasePresentationModel<T> getPresentationModel() {
		return basePresentationModel;
	}
}

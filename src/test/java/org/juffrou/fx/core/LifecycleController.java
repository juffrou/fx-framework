package org.juffrou.fx.core;

import javafx.fxml.FXML;

public class LifecycleController {
	
	private LifecyclePresentationManager presentationManager;
	
	@FXML
	private void save() {
		System.out.println("save");
		presentationManager.save();
	}
	
	@FXML
	private void cancel() {
		System.out.println("cancel");
		presentationManager.cancel();
	}

	public LifecyclePresentationManager getPresentationManager() {
		return presentationManager;
	}

	public void setPresentationManager(LifecyclePresentationManager presentationManager) {
		this.presentationManager = presentationManager;
	}

	
}

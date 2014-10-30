package org.juffrou.fx.reflect;

import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.JuffrouBeanWrapper;

public class FXBeanWrapper extends JuffrouBeanWrapper {

	public FXBeanWrapper(BeanWrapperContext context,
			JuffrouBeanWrapper parentBeanWrapper, String parentBeanProperty) {
		super(context, parentBeanWrapper, parentBeanProperty);
	}

	public FXBeanWrapper(BeanWrapperContext context, Object instance) {
		super(context, instance);
	}

	public FXBeanWrapper(BeanWrapperContext context) {
		super(context);
	}

	public FXBeanWrapper(Class<?> clazz) {
		super(clazz);
	}

	public FXBeanWrapper(Object instance) {
		super(instance);
	}

}

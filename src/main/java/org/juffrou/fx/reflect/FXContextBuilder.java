package org.juffrou.fx.reflect;

import java.lang.reflect.Type;

import net.sf.juffrou.reflect.BeanContextBuilder;
import net.sf.juffrou.reflect.BeanWrapperContext;
import net.sf.juffrou.reflect.CustomizableBeanWrapperFactory;

public class FXContextBuilder implements BeanContextBuilder {

	@Override
	public BeanWrapperContext build(CustomizableBeanWrapperFactory bwFactory, Class clazz, Type... types) {
		return new FXBeanWrapperContext(bwFactory, clazz, types);
	}

}

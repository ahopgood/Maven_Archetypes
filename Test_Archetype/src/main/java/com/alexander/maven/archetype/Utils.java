package com.alexander.maven.archetype;

public class Utils {

	public static <T> T castBean(Object bean, Class<T> beanClass){
		try {
			return beanClass.cast(bean);
		} catch (ClassCastException cce){
			return null;
		}
	}
}

package com.fxkj.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class BeanUtil {
	private static final DozerBeanMapper mapper = new DozerBeanMapper();

	/**
	 * @param source
	 * @param destinationClass
	 * @return
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {

		return mapper.map(source, destinationClass);
	}

	/**
	 * @param sourceList
	 * @param destinationClass
	 * @return
	 */
	public static <T> List<T> mapList(Collection<?> sourceList,
			Class<T> destinationClass) {
		List<T> destinationList = new ArrayList<T>();
		for (Object sourceObject : sourceList) {
			T destinationObject = mapper.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * @param source
	 * @param destinationObject
	 */
	public static void copy(Object source, Object destinationObject) {
		mapper.map(source, destinationObject);
	}

}

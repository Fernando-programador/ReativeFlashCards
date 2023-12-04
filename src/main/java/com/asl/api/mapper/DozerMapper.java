package com.asl.api.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;

public class DozerMapper {
	
	private static Mapper mapper =  DozerBeanMapperBuilder.buildDefault();
	
	
	public static <T> T map(Object source, Class<T> destinationClass) throws MappingException{
	
		return mapper.map(source, destinationClass);
	}
	
}

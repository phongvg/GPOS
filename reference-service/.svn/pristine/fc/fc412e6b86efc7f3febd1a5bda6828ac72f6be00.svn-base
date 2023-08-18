package com.gg.gpos.reference;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.entity.SystemParameter;
import com.gg.gpos.reference.mapper.SystemParameterMapper;

@RunWith(SpringRunner.class)
public class SystemParameterMapperTest {

	@Autowired
	private SystemParameterMapper mapper;
	
	@Test
	public void mapping() {
		SystemParameter entity = new SystemParameter();
		entity.setId(1L);
		entity.setParamName("key");
		entity.setParamValue("value");
		System.out.println("ENTITY: " + entity.toString());
		
		SystemParameterDto dto = mapper.entityToDto(entity);
		System.out.println("DTO: " + dto.toString());
	}

}

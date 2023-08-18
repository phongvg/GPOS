package com.gg.gpos.reference.mapper;

import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.entity.SystemParameter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:19+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class SystemParameterMapperImpl implements SystemParameterMapper {

    @Override
    public SystemParameter dtoToEntity(SystemParameterDto systemParameterDto) {
        if ( systemParameterDto == null ) {
            return null;
        }

        SystemParameter systemParameter = new SystemParameter();

        systemParameter.setId( systemParameterDto.getId() );
        systemParameter.setParamName( systemParameterDto.getParamName() );
        systemParameter.setParamValue( systemParameterDto.getParamValue() );
        systemParameter.setDescription( systemParameterDto.getDescription() );
        systemParameter.setVersion( systemParameterDto.getVersion() );

        return systemParameter;
    }

    @Override
    public SystemParameterDto entityToDto(SystemParameter systemParameter) {
        if ( systemParameter == null ) {
            return null;
        }

        SystemParameterDto systemParameterDto = new SystemParameterDto();

        systemParameterDto.setId( systemParameter.getId() );
        systemParameterDto.setParamName( systemParameter.getParamName() );
        systemParameterDto.setParamValue( systemParameter.getParamValue() );
        systemParameterDto.setDescription( systemParameter.getDescription() );
        systemParameterDto.setVersion( systemParameter.getVersion() );

        return systemParameterDto;
    }
}

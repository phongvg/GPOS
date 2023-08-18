package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.entity.Param;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class GroupParamMapperImpl implements GroupParamMapper {

    @Override
    public GroupParam dtoToEntity(GroupParamDto groupParamDto) {
        if ( groupParamDto == null ) {
            return null;
        }

        GroupParam groupParam = new GroupParam();

        groupParam.setId( groupParamDto.getId() );
        groupParam.setName( groupParamDto.getName() );
        groupParam.setStatus( groupParamDto.isStatus() );
        groupParam.setCreatedBy( groupParamDto.getCreatedBy() );
        groupParam.setCreatedDate( groupParamDto.getCreatedDate() );
        groupParam.setModifiedBy( groupParamDto.getModifiedBy() );
        groupParam.setModifiedDate( groupParamDto.getModifiedDate() );
        groupParam.setParams( paramDtoListToParamList( groupParamDto.getParams() ) );

        return groupParam;
    }

    @Override
    public GroupParamDto entityToDto(GroupParam groupParam) {
        if ( groupParam == null ) {
            return null;
        }

        GroupParamDto groupParamDto = new GroupParamDto();

        groupParamDto.setId( groupParam.getId() );
        groupParamDto.setName( groupParam.getName() );
        groupParamDto.setStatus( groupParam.isStatus() );
        groupParamDto.setCreatedBy( groupParam.getCreatedBy() );
        groupParamDto.setCreatedDate( groupParam.getCreatedDate() );
        groupParamDto.setModifiedBy( groupParam.getModifiedBy() );
        groupParamDto.setModifiedDate( groupParam.getModifiedDate() );
        groupParamDto.setParams( paramListToParamDtoList( groupParam.getParams() ) );

        return groupParamDto;
    }

    @Override
    public ParamDto paramToParamDto(Param param) {
        if ( param == null ) {
            return null;
        }

        ParamDto paramDto = new ParamDto();

        paramDto.setId( param.getId() );
        paramDto.setRestaurantCode( param.getRestaurantCode() );
        paramDto.setType( param.getType() );
        paramDto.setParamCode( param.getParamCode() );
        paramDto.setParamValue( param.getParamValue() );
        paramDto.setDescription( param.getDescription() );
        paramDto.setStatus( param.isStatus() );
        paramDto.setCreatedBy( param.getCreatedBy() );
        paramDto.setCreatedDate( param.getCreatedDate() );
        paramDto.setModifiedBy( param.getModifiedBy() );
        paramDto.setModifiedDate( param.getModifiedDate() );
        paramDto.setName( param.getName() );

        paramDto.setGroupParam( null );

        return paramDto;
    }

    protected Param paramDtoToParam(ParamDto paramDto) {
        if ( paramDto == null ) {
            return null;
        }

        Param param = new Param();

        param.setId( paramDto.getId() );
        param.setRestaurantCode( paramDto.getRestaurantCode() );
        param.setType( paramDto.getType() );
        param.setParamCode( paramDto.getParamCode() );
        param.setParamValue( paramDto.getParamValue() );
        param.setDescription( paramDto.getDescription() );
        param.setStatus( paramDto.isStatus() );
        param.setCreatedBy( paramDto.getCreatedBy() );
        param.setCreatedDate( paramDto.getCreatedDate() );
        param.setModifiedBy( paramDto.getModifiedBy() );
        param.setModifiedDate( paramDto.getModifiedDate() );
        param.setName( paramDto.getName() );
        param.setGroupParam( dtoToEntity( paramDto.getGroupParam() ) );

        return param;
    }

    protected List<Param> paramDtoListToParamList(List<ParamDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Param> list1 = new ArrayList<Param>( list.size() );
        for ( ParamDto paramDto : list ) {
            list1.add( paramDtoToParam( paramDto ) );
        }

        return list1;
    }

    protected List<ParamDto> paramListToParamDtoList(List<Param> list) {
        if ( list == null ) {
            return null;
        }

        List<ParamDto> list1 = new ArrayList<ParamDto>( list.size() );
        for ( Param param : list ) {
            list1.add( paramToParamDto( param ) );
        }

        return list1;
    }
}

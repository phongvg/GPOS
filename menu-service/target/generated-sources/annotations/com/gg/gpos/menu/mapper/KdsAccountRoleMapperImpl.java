package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.KdsAccountRoleDto;
import com.gg.gpos.menu.entity.KdsAccountRole;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class KdsAccountRoleMapperImpl implements KdsAccountRoleMapper {

    @Override
    public KdsAccountRole dtoToEntity(KdsAccountRoleDto kdsAccountRoleDto) {
        if ( kdsAccountRoleDto == null ) {
            return null;
        }

        KdsAccountRole kdsAccountRole = new KdsAccountRole();

        kdsAccountRole.setId( kdsAccountRoleDto.getId() );
        kdsAccountRole.setName( kdsAccountRoleDto.getName() );
        kdsAccountRole.setValue( kdsAccountRoleDto.getValue() );

        return kdsAccountRole;
    }

    @Override
    public KdsAccountRoleDto entityToDto(KdsAccountRole kdsAccountRole) {
        if ( kdsAccountRole == null ) {
            return null;
        }

        KdsAccountRoleDto kdsAccountRoleDto = new KdsAccountRoleDto();

        kdsAccountRoleDto.setId( kdsAccountRole.getId() );
        kdsAccountRoleDto.setValue( kdsAccountRole.getValue() );
        kdsAccountRoleDto.setName( kdsAccountRole.getName() );

        return kdsAccountRoleDto;
    }
}

package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.VersionDto;
import com.gg.gpos.menu.entity.Version;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class VersionMapperImpl implements VersionMapper {

    @Override
    public Version dtoToEntity(VersionDto versionDto) {
        if ( versionDto == null ) {
            return null;
        }

        Version version = new Version();

        version.setId( versionDto.getId() );
        version.setTableName( versionDto.getTableName() );
        version.setVersionNo( versionDto.getVersionNo() );

        return version;
    }

    @Override
    public VersionDto entityToDto(Version version) {
        if ( version == null ) {
            return null;
        }

        VersionDto versionDto = new VersionDto();

        versionDto.setId( version.getId() );
        versionDto.setTableName( version.getTableName() );
        versionDto.setVersionNo( version.getVersionNo() );

        return versionDto;
    }
}

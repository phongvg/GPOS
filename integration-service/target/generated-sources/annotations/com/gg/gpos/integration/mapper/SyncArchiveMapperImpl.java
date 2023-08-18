package com.gg.gpos.integration.mapper;

import com.gg.gpos.integration.dto.SyncArchiveDto;
import com.gg.gpos.integration.entity.SyncArchive;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:41:17+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class SyncArchiveMapperImpl implements SyncArchiveMapper {

    @Override
    public SyncArchive dtoToEntity(SyncArchiveDto syncArchiveDto) {
        if ( syncArchiveDto == null ) {
            return null;
        }

        SyncArchive syncArchive = new SyncArchive();

        syncArchive.setId( syncArchiveDto.getId() );
        syncArchive.setRestaurantCode( syncArchiveDto.getRestaurantCode() );
        syncArchive.setRestaurantName( syncArchiveDto.getRestaurantName() );
        syncArchive.setTypeSync( syncArchiveDto.getTypeSync() );
        syncArchive.setTypeData( syncArchiveDto.getTypeData() );
        syncArchive.setCreatedDate( syncArchiveDto.getCreatedDate() );
        syncArchive.setStatus( syncArchiveDto.getStatus() );
        syncArchive.setResult( syncArchiveDto.getResult() );
        syncArchive.setSyncStartDate( syncArchiveDto.getSyncStartDate() );
        syncArchive.setSyncEndDate( syncArchiveDto.getSyncEndDate() );

        return syncArchive;
    }

    @Override
    public SyncArchiveDto entityToDto(SyncArchive syncArchive) {
        if ( syncArchive == null ) {
            return null;
        }

        SyncArchiveDto syncArchiveDto = new SyncArchiveDto();

        syncArchiveDto.setId( syncArchive.getId() );
        syncArchiveDto.setRestaurantCode( syncArchive.getRestaurantCode() );
        syncArchiveDto.setRestaurantName( syncArchive.getRestaurantName() );
        syncArchiveDto.setTypeSync( syncArchive.getTypeSync() );
        syncArchiveDto.setTypeData( syncArchive.getTypeData() );
        syncArchiveDto.setCreatedDate( syncArchive.getCreatedDate() );
        syncArchiveDto.setStatus( syncArchive.getStatus() );
        syncArchiveDto.setResult( syncArchive.getResult() );
        syncArchiveDto.setSyncStartDate( syncArchive.getSyncStartDate() );
        syncArchiveDto.setSyncEndDate( syncArchive.getSyncEndDate() );

        return syncArchiveDto;
    }
}

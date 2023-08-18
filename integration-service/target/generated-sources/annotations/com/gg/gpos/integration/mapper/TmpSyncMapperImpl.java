package com.gg.gpos.integration.mapper;

import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.dto.TmpSyncDto;
import com.gg.gpos.integration.entity.Sync;
import com.gg.gpos.integration.entity.TmpSync;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:41:16+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class TmpSyncMapperImpl implements TmpSyncMapper {

    @Override
    public TmpSync dtoToEntity(TmpSyncDto tmpSyncDto) {
        if ( tmpSyncDto == null ) {
            return null;
        }

        TmpSync tmpSync = new TmpSync();

        tmpSync.setId( tmpSyncDto.getId() );
        tmpSync.setRestaurantCode( tmpSyncDto.getRestaurantCode() );
        tmpSync.setType( tmpSyncDto.getType() );
        tmpSync.setCatalogId( tmpSyncDto.getCatalogId() );
        tmpSync.setValue( tmpSyncDto.getValue() );
        tmpSync.setSync( syncDtoToSync( tmpSyncDto.getSync() ) );

        return tmpSync;
    }

    @Override
    public TmpSyncDto entityToDto(TmpSync tmpSync) {
        if ( tmpSync == null ) {
            return null;
        }

        TmpSyncDto tmpSyncDto = new TmpSyncDto();

        tmpSyncDto.setId( tmpSync.getId() );
        tmpSyncDto.setRestaurantCode( tmpSync.getRestaurantCode() );
        tmpSyncDto.setType( tmpSync.getType() );
        tmpSyncDto.setCatalogId( tmpSync.getCatalogId() );
        tmpSyncDto.setValue( tmpSync.getValue() );
        tmpSyncDto.setSync( syncToSyncDto( tmpSync.getSync() ) );

        return tmpSyncDto;
    }

    @Override
    public SyncDto syncToSyncDto(Sync sync) {
        if ( sync == null ) {
            return null;
        }

        SyncDto syncDto = new SyncDto();

        syncDto.setId( sync.getId() );
        syncDto.setRestaurantCode( sync.getRestaurantCode() );
        syncDto.setRestaurantName( sync.getRestaurantName() );
        syncDto.setTypeSync( sync.getTypeSync() );
        syncDto.setTypeData( sync.getTypeData() );
        syncDto.setCreatedDate( sync.getCreatedDate() );
        syncDto.setStatus( sync.getStatus() );
        syncDto.setResult( sync.getResult() );
        syncDto.setCatalogId( sync.getCatalogId() );
        syncDto.setOverride( sync.getOverride() );
        syncDto.setSyncStartDate( sync.getSyncStartDate() );
        syncDto.setSyncEndDate( sync.getSyncEndDate() );

        syncDto.setTmpSyncs( null );

        return syncDto;
    }

    protected List<TmpSync> tmpSyncDtoListToTmpSyncList(List<TmpSyncDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TmpSync> list1 = new ArrayList<TmpSync>( list.size() );
        for ( TmpSyncDto tmpSyncDto : list ) {
            list1.add( dtoToEntity( tmpSyncDto ) );
        }

        return list1;
    }

    protected Sync syncDtoToSync(SyncDto syncDto) {
        if ( syncDto == null ) {
            return null;
        }

        Sync sync = new Sync();

        sync.setId( syncDto.getId() );
        sync.setRestaurantCode( syncDto.getRestaurantCode() );
        sync.setRestaurantName( syncDto.getRestaurantName() );
        sync.setTypeSync( syncDto.getTypeSync() );
        sync.setTypeData( syncDto.getTypeData() );
        sync.setCreatedDate( syncDto.getCreatedDate() );
        sync.setStatus( syncDto.getStatus() );
        sync.setResult( syncDto.getResult() );
        sync.setCatalogId( syncDto.getCatalogId() );
        sync.setOverride( syncDto.getOverride() );
        sync.setTmpSyncs( tmpSyncDtoListToTmpSyncList( syncDto.getTmpSyncs() ) );
        sync.setSyncStartDate( syncDto.getSyncStartDate() );
        sync.setSyncEndDate( syncDto.getSyncEndDate() );

        return sync;
    }
}

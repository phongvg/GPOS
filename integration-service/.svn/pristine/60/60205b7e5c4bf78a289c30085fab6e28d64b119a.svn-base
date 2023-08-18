package com.gg.gpos.integration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.dto.TmpSyncDto;
import com.gg.gpos.integration.entity.Sync;
import com.gg.gpos.integration.entity.TmpSync;

@Mapper(componentModel = "spring")
public interface TmpSyncMapper {
	TmpSync dtoToEntity(TmpSyncDto tmpSyncDto);
	TmpSyncDto entityToDto(TmpSync tmpSync); 
	
	@Mapping(target = "tmpSyncs", expression = "java(null)")
	SyncDto syncToSyncDto(Sync sync);
}

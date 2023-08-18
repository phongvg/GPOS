package com.gg.gpos.integration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.dto.TmpSyncDto;
import com.gg.gpos.integration.entity.Sync;
import com.gg.gpos.integration.entity.TmpSync;

@Mapper(componentModel = "spring")
public interface SyncMapper {
	Sync dtoToEntity(SyncDto syncDto);
	SyncDto entityToDto(Sync sync);
	
	@Mapping(target = "sync", expression = "java(null)")
	TmpSyncDto tmpSyncToTmpSyncDto(TmpSync tmpSync);
}

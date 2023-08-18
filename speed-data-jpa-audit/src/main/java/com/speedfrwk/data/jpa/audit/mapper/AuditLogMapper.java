package com.speedfrwk.data.jpa.audit.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.audit.dto.AuditLogDto;
import com.speedfrwk.data.jpa.audit.AuditLog;

@Mapper(componentModel="spring")
public interface AuditLogMapper {
	AuditLog dtoToEntity(AuditLogDto auditLogDto);
	AuditLogDto entityToDto(AuditLog auditLog);
}

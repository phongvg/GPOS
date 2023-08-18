package com.speedfrwk.data.jpa.audit.mapper;

import com.gg.gpos.audit.dto.AuditLogDto;
import com.speedfrwk.data.jpa.audit.Action;
import com.speedfrwk.data.jpa.audit.AuditLog;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:39:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class AuditLogMapperImpl implements AuditLogMapper {

    @Override
    public AuditLog dtoToEntity(AuditLogDto auditLogDto) {
        if ( auditLogDto == null ) {
            return null;
        }

        AuditLog auditLog = new AuditLog();

        auditLog.setId( auditLogDto.getId() );
        auditLog.setContent( auditLogDto.getContent() );
        auditLog.setModifiedBy( auditLogDto.getModifiedBy() );
        auditLog.setModifiedDate( auditLogDto.getModifiedDate() );
        if ( auditLogDto.getAction() != null ) {
            auditLog.setAction( Enum.valueOf( Action.class, auditLogDto.getAction() ) );
        }

        return auditLog;
    }

    @Override
    public AuditLogDto entityToDto(AuditLog auditLog) {
        if ( auditLog == null ) {
            return null;
        }

        AuditLogDto auditLogDto = new AuditLogDto();

        auditLogDto.setId( auditLog.getId() );
        auditLogDto.setContent( auditLog.getContent() );
        auditLogDto.setModifiedBy( auditLog.getModifiedBy() );
        auditLogDto.setModifiedDate( auditLog.getModifiedDate() );
        if ( auditLog.getAction() != null ) {
            auditLogDto.setAction( auditLog.getAction().name() );
        }

        return auditLogDto;
    }
}

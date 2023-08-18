package com.gg.gpos.reference.mapper;

import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.entity.Attachment;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:19+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class AttachmentMapperImpl implements AttachmentMapper {

    @Override
    public Attachment dtoToEntity(AttachmentDto attachmentDto) {
        if ( attachmentDto == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setId( attachmentDto.getId() );
        attachment.setModuleId( attachmentDto.getModuleId() );
        attachment.setModuleType( attachmentDto.getModuleType() );
        attachment.setFunctionType( attachmentDto.getFunctionType() );
        attachment.setName( attachmentDto.getName() );
        attachment.setUrl( attachmentDto.getUrl() );
        attachment.setAbsolutePath( attachmentDto.getAbsolutePath() );
        attachment.setFileName( attachmentDto.getFileName() );
        attachment.setFileType( attachmentDto.getFileType() );
        attachment.setVersion( attachmentDto.getVersion() );
        attachment.setRestaurantCode( attachmentDto.getRestaurantCode() );
        attachment.setRestaurantName( attachmentDto.getRestaurantName() );
        attachment.setSyncStatus( attachmentDto.getSyncStatus() );
        attachment.setModuleCode( attachmentDto.getModuleCode() );

        return attachment;
    }

    @Override
    public AttachmentDto entityToDto(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentDto attachmentDto = new AttachmentDto();

        attachmentDto.setId( attachment.getId() );
        attachmentDto.setModuleId( attachment.getModuleId() );
        attachmentDto.setModuleType( attachment.getModuleType() );
        attachmentDto.setFunctionType( attachment.getFunctionType() );
        attachmentDto.setName( attachment.getName() );
        attachmentDto.setUrl( attachment.getUrl() );
        attachmentDto.setAbsolutePath( attachment.getAbsolutePath() );
        attachmentDto.setFileName( attachment.getFileName() );
        attachmentDto.setFileType( attachment.getFileType() );
        attachmentDto.setVersion( attachment.getVersion() );
        attachmentDto.setSyncStatus( attachment.getSyncStatus() );
        attachmentDto.setRestaurantCode( attachment.getRestaurantCode() );
        attachmentDto.setRestaurantName( attachment.getRestaurantName() );
        attachmentDto.setModuleCode( attachment.getModuleCode() );

        return attachmentDto;
    }
}

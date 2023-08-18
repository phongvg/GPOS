package com.gg.gpos.user.mapper;

import com.gg.gpos.user.dto.PositionDto;
import com.gg.gpos.user.entity.Position;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:39:23+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position dtoToEntity(PositionDto positionDto) {
        if ( positionDto == null ) {
            return null;
        }

        Position position = new Position();

        position.setId( positionDto.getId() );
        position.setName( positionDto.getName() );

        return position;
    }

    @Override
    public PositionDto entityToDto(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        positionDto.setId( position.getId() );
        positionDto.setName( position.getName() );

        return positionDto;
    }
}

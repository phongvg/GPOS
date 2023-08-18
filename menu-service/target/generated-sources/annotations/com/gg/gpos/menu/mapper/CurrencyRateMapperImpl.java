package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CurrencyRateDto;
import com.gg.gpos.menu.entity.CurrencyRate;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CurrencyRateMapperImpl implements CurrencyRateMapper {

    @Override
    public CurrencyRate dtoToEntity(CurrencyRateDto serviceRateDto) {
        if ( serviceRateDto == null ) {
            return null;
        }

        CurrencyRate currencyRate = new CurrencyRate();

        currencyRate.setId( serviceRateDto.getId() );
        currencyRate.setItemIdent( serviceRateDto.getItemIdent() );
        currencyRate.setStatus( serviceRateDto.getStatus() );
        currencyRate.setRate( serviceRateDto.getRate() );

        return currencyRate;
    }

    @Override
    public CurrencyRateDto entityToDto(CurrencyRate serviceRate) {
        if ( serviceRate == null ) {
            return null;
        }

        CurrencyRateDto currencyRateDto = new CurrencyRateDto();

        currencyRateDto.setId( serviceRate.getId() );
        currencyRateDto.setItemIdent( serviceRate.getItemIdent() );
        currencyRateDto.setStatus( serviceRate.getStatus() );
        currencyRateDto.setRate( serviceRate.getRate() );

        return currencyRateDto;
    }
}

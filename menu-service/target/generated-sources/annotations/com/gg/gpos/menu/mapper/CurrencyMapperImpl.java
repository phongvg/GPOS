package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CurrencyDto;
import com.gg.gpos.menu.entity.Currency;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public Currency dtoToEntity(CurrencyDto currencyDto) {
        if ( currencyDto == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setId( currencyDto.getId() );
        currency.setItemIdent( currencyDto.getItemIdent() );
        currency.setStatus( currencyDto.getStatus() );
        currency.setRateClass( currencyDto.getRateClass() );
        currency.setCode( currencyDto.getCode() );

        return currency;
    }

    @Override
    public CurrencyDto entityToDto(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setId( currency.getId() );
        currencyDto.setItemIdent( currency.getItemIdent() );
        currencyDto.setStatus( currency.getStatus() );
        currencyDto.setRateClass( currency.getRateClass() );
        currencyDto.setCode( currency.getCode() );

        return currencyDto;
    }
}

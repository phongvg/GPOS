package com.gg.gpos.reference.mapper;

import com.gg.gpos.reference.dto.TaxDto;
import com.gg.gpos.reference.entity.Tax;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:19+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class TaxMapperImpl implements TaxMapper {

    @Override
    public Tax dtoToEntity(TaxDto taxDto) {
        if ( taxDto == null ) {
            return null;
        }

        Tax tax = new Tax();

        tax.setId( taxDto.getId() );
        tax.setTaxNo( taxDto.getTaxNo() );
        tax.setRepresentativeName( taxDto.getRepresentativeName() );
        tax.setCompanyName( taxDto.getCompanyName() );
        tax.setAddress( taxDto.getAddress() );
        tax.setEmail( taxDto.getEmail() );
        tax.setPhone( taxDto.getPhone() );

        return tax;
    }

    @Override
    public TaxDto entityToDto(Tax tax) {
        if ( tax == null ) {
            return null;
        }

        TaxDto taxDto = new TaxDto();

        taxDto.setId( tax.getId() );
        taxDto.setTaxNo( tax.getTaxNo() );
        taxDto.setRepresentativeName( tax.getRepresentativeName() );
        taxDto.setCompanyName( tax.getCompanyName() );
        taxDto.setAddress( tax.getAddress() );
        taxDto.setEmail( tax.getEmail() );
        taxDto.setPhone( tax.getPhone() );

        return taxDto;
    }
}

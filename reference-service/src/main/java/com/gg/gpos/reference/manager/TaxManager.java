package com.gg.gpos.reference.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gpos.reference.dto.TaxDto;
import com.gg.gpos.reference.entity.Tax;
import com.gg.gpos.reference.mapper.TaxMapper;
import com.gg.gpos.reference.repository.TaxRepository;

@Service
@Transactional
public class TaxManager {
	@Autowired
	private TaxRepository taxRepository;
	
	@Autowired
	private TaxMapper taxMapper;
	
	public TaxDto get(String taxNo) {
		return Optional.ofNullable(taxRepository.findByTaxNo(taxNo)).map(taxMapper::entityToDto).orElse(null);
	}
	
	public List<String> save(TaxDto taxDto) {
		List<String> errors = checkValidate(taxDto);
		if(errors.isEmpty()) {
			Tax tax = taxRepository.findByTaxNo(taxDto.getTaxNo());
			if(tax == null) {
				tax = Optional.ofNullable(taxDto).map(taxMapper::dtoToEntity).orElse(null);
			} else {
				tax.setAddress(taxDto.getAddress());
				tax.setCompanyName(taxDto.getCompanyName());
				tax.setEmail(taxDto.getEmail());
				tax.setPhone(taxDto.getPhone());
				tax.setRepresentativeName(taxDto.getRepresentativeName());
			}
			taxRepository.save(tax);
		}
		return errors;
	}

	private List<String> checkValidate(TaxDto taxDto) {
		List<String> errors = new ArrayList<>();
		
		if(StringUtils.isBlank(taxDto.getTaxNo())) {
			errors.add("tax.taxNo.required");
		}
		if(StringUtils.isBlank(taxDto.getCompanyName())) {
			errors.add("tax.companyName.required");
		}
		if(StringUtils.isBlank(taxDto.getAddress())) {
			errors.add("tax.address.required");
		}
		return errors;
	}
}

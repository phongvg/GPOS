package com.gg.gpos.res.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gpos.res.dto.BrandDto;
import com.gg.gpos.res.entity.Brand;
import com.gg.gpos.res.mapper.BrandMapper;
import com.gg.gpos.res.repository.BrandRepository;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Service
public class BrandManager {
	private BrandRepository brandRepository;
	@Autowired
	public void setBrandRepository(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}
	private BrandMapper brandMapper;
	@Autowired
	public void setBrandMapper(BrandMapper brandMapper) {
		this.brandMapper = brandMapper;
	}
	
	public BrandDto get(Long id) {
		return brandRepository.findById(id).map(brandMapper::entityToDto).orElse(null);
	}
	
	public List<BrandDto> gets() {
		return brandRepository.findAll().stream().map(brandMapper::entityToDto).collect(Collectors.toList());
	}
	
	public BrandDto save(BrandDto brandDto) {
		Brand brand = Optional.ofNullable(brandDto).map(brandMapper::dtoToEntity).orElse(null);
		if (brand != null) {
			return Optional.ofNullable(brandRepository.save(brand)).map(brandMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
}
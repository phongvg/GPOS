package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gg.gpos.menu.dto.EmployeeDto;
import com.gg.gpos.menu.entity.Employee;
import com.gg.gpos.menu.mapper.EmployeeMapper;
import com.gg.gpos.menu.repository.EmployeeRepository;
import com.gg.gpos.menu.specification.ReferenceSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EmployeeManager {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ReferenceSpecification<Employee> referenceSpecification;

	public Page<EmployeeDto> gets(EmployeeDto criteria) {
		log.debug("PROCESS: GETS EMPLOYEE, EMPLOYEE_DTO: {}", criteria);
		Specification<Employee> spec = Specification.where(referenceSpecification.search(criteria.getCode(), criteria.getName(), criteria.getStatus()));
		Page<Employee> page = employeeRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(employeeMapper::entityToDto).collect(Collectors.toList()), PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public void save(List<EmployeeDto> employeeDtos) {
		log.debug("PROCESS: SAVE EMPLOYEE_DTO, EMPLOYEE_DTOS: {}", employeeDtos);
		if (!employeeDtos.isEmpty()) {
			employeeDtos.stream().forEach(f -> {
				Employee employee = Optional.ofNullable(f).map(employeeMapper::dtoToEntity).orElse(null);
				if (employee != null) {
					employeeRepository.save(employee);
				}
			});
		}
	}
}

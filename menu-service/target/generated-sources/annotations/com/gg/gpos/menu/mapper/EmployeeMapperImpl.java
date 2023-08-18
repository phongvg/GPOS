package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.EmployeeDto;
import com.gg.gpos.menu.entity.Employee;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee dtoToEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setItemId( employeeDto.getItemId() );
        employee.setParentId( employeeDto.getParentId() );
        employee.setCode( employeeDto.getCode() );
        employee.setName( employeeDto.getName() );
        employee.setStatus( employeeDto.getStatus() );
        employee.setLogin( employeeDto.isLogin() );
        employee.setObjectSifr( employeeDto.getObjectSifr() );
        employee.setFlags( employeeDto.getFlags() );

        return employee;
    }

    @Override
    public EmployeeDto entityToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setItemId( employee.getItemId() );
        employeeDto.setParentId( employee.getParentId() );
        employeeDto.setCode( employee.getCode() );
        employeeDto.setName( employee.getName() );
        employeeDto.setStatus( employee.getStatus() );
        employeeDto.setLogin( employee.isLogin() );
        employeeDto.setObjectSifr( employee.getObjectSifr() );
        employeeDto.setFlags( employee.getFlags() );

        return employeeDto;
    }
}

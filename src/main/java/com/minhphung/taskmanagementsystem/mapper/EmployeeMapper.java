package com.minhphung.taskmanagementsystem.mapper;

import com.minhphung.taskmanagementsystem.dto.EmployeeDto;
import com.minhphung.taskmanagementsystem.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto employeeDto);
}

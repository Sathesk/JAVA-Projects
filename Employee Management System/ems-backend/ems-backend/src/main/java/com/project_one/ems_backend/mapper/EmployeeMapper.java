package com.project_one.ems_backend.mapper;

import com.project_one.ems_backend.dto.EmployeeDTO;
import com.project_one.ems_backend.entity.Employee;

public class EmployeeMapper {

    //two methods

    //get the employee object map to employee DTO
    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
                );
    }
}

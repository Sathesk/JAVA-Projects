package com.project_one.ems_backend.service;

import com.project_one.ems_backend.dto.EmployeeDTO;

import java.util.List;


public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updateEmployee);
    void deleteEmployee(Long employeeId);
}

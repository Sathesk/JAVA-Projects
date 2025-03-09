package com.project_one.ems_backend.service;

import com.project_one.ems_backend.dto.EmployeeDTO;
import com.project_one.ems_backend.entity.Employee;
import com.project_one.ems_backend.exception.ResourceNotFoundException;
import com.project_one.ems_backend.mapper.EmployeeMapper;
import com.project_one.ems_backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    //postRest
    private EmployeeRepository employeeRepository;
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee createEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(createEmployee);
    }
    //getRest-get employee by id
    public EmployeeDTO getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given Id: " + employeeId));
        //Employee is an optional class, thats why we're extends the code using .orElseThrow
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    //getRest -get all employee
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    //updateEmployee
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updateEmployee){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee is not exist with the given id:" + employeeId));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    //delete
    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee is not exist with the given id:" + employeeId));

        employeeRepository.deleteById(employeeId);
    }
}

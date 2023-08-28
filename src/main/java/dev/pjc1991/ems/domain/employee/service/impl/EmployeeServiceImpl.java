package dev.pjc1991.ems.domain.employee.service.impl;

import dev.pjc1991.ems.domain.employee.dto.EmployeeUpdateRequest;
import dev.pjc1991.ems.domain.employee.dto.SalaryRaiseRequest;
import dev.pjc1991.ems.domain.employee.entity.Location;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import dev.pjc1991.ems.domain.employee.dto.EmployeeResponse;
import dev.pjc1991.ems.domain.employee.entity.Department;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import dev.pjc1991.ems.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeResponseById(Integer id) {
        return new EmployeeResponse(getEmployeeById(id));
    }

    @Override
    public List<JobHistory> getJobHistoryByEmployeeId(Integer id) {
        return null;
    }

    @Override
    public Location getLocationById(Integer id) {
        return null;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> raiseSalaryByDepartmentId(SalaryRaiseRequest request) {
        return null;
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateRequest request) {
        return null;
    }
}

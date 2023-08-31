package dev.pjc1991.ems.domain.employee.service.impl;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.entity.*;
import dev.pjc1991.ems.domain.employee.repository.*;
import dev.pjc1991.ems.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LocationRepository locationRepository;
    private final JobRepository jobRepository;
    private final JobHistoryRepositoryCustom jobHistoryRepositoryCustom;

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
    @Transactional(readOnly = true)
    public Page<JobHistory> getJobHistoryByEmployeeId(EmployeeSearchPageRequest request) {
        if (request.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(
                () -> new IllegalArgumentException("Employee does not exist")
        );

        return jobHistoryRepositoryCustom.getJobHistoryByEmployeeId(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobHistoryResponse> getJobHistoryResponseByEmployeeId(EmployeeSearchPageRequest request) {
        return this.getJobHistoryByEmployeeId(request)
                .map(JobHistoryResponse::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Location does not exist")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public LocationResponse getLocationResponseById(Integer id) {
        return new LocationResponse(getLocationById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Department does not exist")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentResponseById(Integer id) {
        return new DepartmentResponse(this.getDepartmentById(id));
    }

    @Override
    public Set<Employee> raiseSalaryByDepartmentId(SalaryRaiseRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(
                () -> new IllegalArgumentException("Department does not exist")
        );

        department.raiseSalary(request.getRaisePercentage());

        return department.getEmployees();
    }

    @Override
    public Set<EmployeeResponse> raiseSalaryResponseByDepartmentId(SalaryRaiseRequest request) {
        return this.raiseSalaryByDepartmentId(request)
                .stream()
                .map(EmployeeResponse::new)
                .collect(java.util.stream.Collectors.toSet());
    }


    @Override
    public Employee updateEmployee(EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("Employee does not exist")
        );

        if(request.getJobId() != null) {
            Job newJob = jobRepository.findById(request.getJobId()).orElseThrow(
                    () -> new IllegalArgumentException("Job does not exist")
            );
            request.setJob(newJob);
        }

        if(request.getManagerId() != null) {
            Employee newManager = employeeRepository.findById(request.getManagerId()).orElseThrow(
                    () -> new IllegalArgumentException("Manager does not exist")
            );
            request.setManager(newManager);
        }

        if(request.getDepartmentId() != null) {
            Department newDepartment = departmentRepository.findById(request.getDepartmentId()).orElseThrow(
                    () -> new IllegalArgumentException("Department does not exist")
            );
            request.setDepartment(newDepartment);
        }

        employee.updateEmployee(request);

        return employee;
    }

    @Override
    public EmployeeResponse updateEmployeeResponse(EmployeeUpdateRequest request) {
        return new EmployeeResponse(this.updateEmployee(request));
    }
}

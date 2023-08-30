package dev.pjc1991.ems.domain.employee.service.impl;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.entity.Location;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import dev.pjc1991.ems.domain.employee.entity.Department;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import dev.pjc1991.ems.domain.employee.repository.JobHistoryRepositoryCustom;
import dev.pjc1991.ems.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
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

package dev.pjc1991.ems.domain.employee.service;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.entity.Location;
import dev.pjc1991.ems.domain.employee.entity.Department;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    EmployeeResponse getEmployeeResponseById(Integer id);

    Page<JobHistory> getJobHistoryByEmployeeId(EmployeeSearchPageRequest request);

    Page<JobHistoryResponse> getJobHistoryResponseByEmployeeId(EmployeeSearchPageRequest request);

    Location getLocationById(Integer id);

    LocationResponse getLocationResponseById(Integer id);

    Department getDepartmentById(Integer id);

    DepartmentResponse getDepartmentResponseById(Integer id);

    Set<Employee> raiseSalaryByDepartmentId(SalaryRaiseRequest request);

    Set<EmployeeResponse> raiseSalaryResponseByDepartmentId(SalaryRaiseRequest request);

    Employee updateEmployee(EmployeeUpdateRequest request);

    EmployeeResponse updateEmployeeResponse(EmployeeUpdateRequest request);

}

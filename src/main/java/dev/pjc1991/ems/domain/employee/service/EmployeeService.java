package dev.pjc1991.ems.domain.employee.service;

import dev.pjc1991.ems.domain.employee.dto.EmployeeUpdateRequest;
import dev.pjc1991.ems.domain.employee.dto.SalaryRaiseRequest;
import dev.pjc1991.ems.domain.employee.entity.Location;
import dev.pjc1991.ems.domain.employee.dto.EmployeeResponse;
import dev.pjc1991.ems.domain.employee.entity.Department;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    EmployeeResponse getEmployeeResponseById(Integer id);

    List<JobHistory> getJobHistoryByEmployeeId(Integer id);

    Location getLocationById(Integer id);

    Department getDepartmentById(Integer id);

    List<Employee> raiseSalaryByDepartmentId(SalaryRaiseRequest request);

    Employee updateEmployee(EmployeeUpdateRequest request);

}

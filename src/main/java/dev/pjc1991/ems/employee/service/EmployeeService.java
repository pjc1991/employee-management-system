package dev.pjc1991.ems.employee.service;

import dev.pjc1991.ems.employee.dto.EmployeeUpdateRequest;
import dev.pjc1991.ems.employee.dto.SalaryRaiseRequest;
import dev.pjc1991.ems.employee.entity.Department;
import dev.pjc1991.ems.employee.entity.Employee;
import dev.pjc1991.ems.employee.entity.JobHistory;
import dev.pjc1991.ems.employee.entity.Location;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    List<JobHistory> getJobHistoryByEmployeeId(Integer id);

    Location getLocationById(Integer id);

    Department getDepartmentById(Integer id);

    List<Employee> raiseSalaryByDepartmentId(SalaryRaiseRequest request);

    Employee updateEmployee(EmployeeUpdateRequest request);

}

package dev.pjc1991.ems.domain.employee.dto;

import dev.pjc1991.ems.domain.employee.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EmployeeResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private String managerName;
    private String departmentName;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.hireDate = employee.getHireDate();
        this.jobTitle = employee.getJob().getJobTitle();
        this.salary = employee.getSalary();
        this.commissionPct = employee.getCommissionPct();
        this.managerName = employee.getManager() != null ? employee.getManager().getFirstName() + " " + employee.getManager().getLastName() : null;
        this.departmentName = employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : null;
    }

    public static Set<EmployeeResponse> from(Set<Employee> employees) {
        return employees.stream().map(EmployeeResponse::new).collect(java.util.stream.Collectors.toSet());
    }
}

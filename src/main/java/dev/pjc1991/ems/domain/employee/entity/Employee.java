package dev.pjc1991.ems.domain.employee.entity;

import dev.pjc1991.ems.domain.employee.dto.EmployeeUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Size(max = 20)
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Size(max = 25)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Size(max = 25)
    @NotNull
    @Column(name = "email", nullable = false, length = 25)
    private String email;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @NotNull
    @Column(name = "salary", nullable = false, precision = 8, scale = 2)
    private BigDecimal salary;

    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;


    public void raiseSalary(Integer raisePercentage) {
        BigDecimal maxSalary = this.job.getMaxSalary();
        BigDecimal minSalary = this.job.getMinSalary();

        if (raisePercentage == null) {
            throw new IllegalArgumentException("Raise percentage cannot be null");
        }

        if (raisePercentage < 0) {
            throw new IllegalArgumentException("Raise percentage cannot be negative");
        }

        if (this.salary.compareTo(maxSalary) >= 0) {
            throw new IllegalArgumentException("Employee salary is already at the maximum");
        }

        if (this.salary.compareTo(minSalary) < 0) {
            throw new IllegalArgumentException("Employee salary is already at the minimum");
        }

        BigDecimal newSalary = this.salary.multiply(BigDecimal.valueOf(1 + (raisePercentage / 100.0)));
        this.salary = newSalary.compareTo(maxSalary) > 0 ? maxSalary : newSalary;
    }

    public void updateEmployee(EmployeeUpdateRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.phoneNumber = request.getPhoneNumber();
        this.job = request.getJob();
        this.manager = request.getManager();
        this.department = request.getDepartment();
        this.salary = this.setSalary(request.getSalary());
        this.commissionPct = BigDecimal.valueOf(request.getCommissionPct());
    }

    private BigDecimal setSalary(Integer salary) {
        if (salary == null) {
            throw new IllegalArgumentException("Salary cannot be null");
        }

        BigDecimal maxSalary = this.job.getMaxSalary();
        BigDecimal minSalary = this.job.getMinSalary();

        if (salary.compareTo(maxSalary.intValue()) > 0) {
            throw new IllegalArgumentException("Salary cannot be greater than the maximum salary for the job");
        }

        if (salary.compareTo(minSalary.intValue()) < 0) {
            throw new IllegalArgumentException("Salary cannot be less than the minimum salary for the job");
        }

        return BigDecimal.valueOf(salary);
    }
}
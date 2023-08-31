package dev.pjc1991.ems.domain.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "departments")
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "department_name", nullable = false, length = 30)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;


    public void raiseSalary(Integer raisePercentage) {
        if (raisePercentage == null) {
            throw new IllegalArgumentException("Raise percentage cannot be null");
        }

        if (raisePercentage < 0) {
            throw new IllegalArgumentException("Raise percentage cannot be negative");
        }

        if (this.manager == null) {
            throw new IllegalArgumentException("Department does not have a manager");
        }

        this.employees.forEach(employee -> {
            employee.raiseSalary(raisePercentage);
        });
    }
}
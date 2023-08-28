package dev.pjc1991.ems.domain.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Mapping for DB view
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Entity
@Table(name = "emp_details_view")
public class EmpDetailsView {

    @Id
    private UUID id;

    @Column(name = "employee_id", columnDefinition = "int UNSIGNED not null")
    private Integer employeeId;

    @Size(max = 10)
    @NotNull
    @Column(name = "job_id", nullable = false, length = 10)
    private String jobId;

    @Column(name = "manager_id", columnDefinition = "int UNSIGNED")
    private Integer managerId;

    @Column(name = "department_id", columnDefinition = "int UNSIGNED")
    private Integer departmentId;

    @Column(name = "location_id", columnDefinition = "int UNSIGNED")
    private Integer locationId;

    @Size(max = 2)
    @NotNull
    @Column(name = "country_id", nullable = false, length = 2)
    private String countryId;

    @Size(max = 20)
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Size(max = 25)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @NotNull
    @Column(name = "salary", nullable = false, precision = 8, scale = 2)
    private BigDecimal salary;

    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @Size(max = 30)
    @NotNull
    @Column(name = "department_name", nullable = false, length = 30)
    private String departmentName;

    @Size(max = 35)
    @NotNull
    @Column(name = "job_title", nullable = false, length = 35)
    private String jobTitle;

    @Size(max = 30)
    @NotNull
    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Size(max = 25)
    @Column(name = "state_province", length = 25)
    private String stateProvince;

    @Size(max = 40)
    @Column(name = "country_name", length = 40)
    private String countryName;

    @Size(max = 25)
    @Column(name = "region_name", length = 25)
    private String regionName;

}
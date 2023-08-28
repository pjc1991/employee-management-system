package dev.pjc1991.ems.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Size(max = 10)
    @Column(name = "job_id", nullable = false, length = 10)
    private String jobId;

    @Size(max = 35)
    @NotNull
    @Column(name = "job_title", nullable = false, length = 35)
    private String jobTitle;

    @Column(name = "min_salary", precision = 8)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 8)
    private BigDecimal maxSalary;

}
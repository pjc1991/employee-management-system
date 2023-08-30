package dev.pjc1991.ems.domain.employee.dto;

import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class JobHistoryResponse {

    private Integer id;
    private Integer employeeId;
    private String employeeName;
    private String jobId;
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer departmentId;
    private String departmentName;

    public JobHistoryResponse(JobHistory entity) {
        this.id = entity.getId();
        this.employeeId = entity.getEmployee().getId();
        this.employeeName = entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName();
        this.jobId = entity.getJob().getJobId();
        this.jobTitle = entity.getJob().getJobTitle();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.departmentId = entity.getDepartment().getId();
        this.departmentName = entity.getDepartment().getDepartmentName();
    }
}

package dev.pjc1991.ems.domain.employee.dto;

import dev.pjc1991.ems.domain.employee.entity.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DepartmentResponse {
    private Integer id;
    private String departmentName;
    private String managerName;
    private String locationName;
    private List<EmployeeResponse> employees;

    public DepartmentResponse(Department entity) {
        this.id = entity.getId();
        this.departmentName = entity.getDepartmentName();
        this.managerName = entity.getManager() != null ? entity.getManager().getFirstName() + " " + entity.getManager().getLastName() : null;
        this.locationName = entity.getLocation().getCity();
        this.employees = EmployeeResponse.from(entity.getEmployees());
    }
}

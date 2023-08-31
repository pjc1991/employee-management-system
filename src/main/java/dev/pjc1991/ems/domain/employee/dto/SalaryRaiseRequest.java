package dev.pjc1991.ems.domain.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryRaiseRequest {

        private Integer departmentId;
        private Integer raisePercentage;
}

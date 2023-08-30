package dev.pjc1991.ems.domain.employee.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class EmployeeSearchPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;
    private Integer employeeId;

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param pageNumber zero-based page number, must not be negative.
     * @param pageSize   the size of the page to be returned, must be greater than 0.
     * @param sort       must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    public EmployeeSearchPageRequest(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize, sort);
    }

    public EmployeeSearchPageRequest() {
        super(0, 10, Sort.unsorted());
    }
}

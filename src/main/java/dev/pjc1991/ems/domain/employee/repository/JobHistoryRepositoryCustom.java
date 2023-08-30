package dev.pjc1991.ems.domain.employee.repository;

import com.querydsl.core.QueryResults;
import dev.pjc1991.ems.domain.employee.dto.EmployeeSearchPageRequest;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import dev.pjc1991.ems.domain.employee.entity.QJobHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class JobHistoryRepositoryCustom extends QuerydslRepositorySupport {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public JobHistoryRepositoryCustom() {
        super(JobHistory.class);
    }

    public Page<JobHistory> getJobHistoryByEmployeeId(EmployeeSearchPageRequest request) {
        QJobHistory jobHistory = QJobHistory.jobHistory;

        QueryResults<JobHistory> results = from(jobHistory)
                .where(jobHistory.employee.id.eq(request.getEmployeeId()))
                .offset(request.getOffset())
                .limit(request.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), request, results.getTotal());
    }
}

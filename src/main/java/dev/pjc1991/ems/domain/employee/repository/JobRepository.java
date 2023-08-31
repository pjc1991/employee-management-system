package dev.pjc1991.ems.domain.employee.repository;

import dev.pjc1991.ems.domain.employee.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
}

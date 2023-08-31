package dev.pjc1991.ems.domain.employee.repository;

import dev.pjc1991.ems.domain.employee.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

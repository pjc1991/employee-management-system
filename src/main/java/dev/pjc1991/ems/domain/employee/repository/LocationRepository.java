package dev.pjc1991.ems.domain.employee.repository;

import dev.pjc1991.ems.domain.employee.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}

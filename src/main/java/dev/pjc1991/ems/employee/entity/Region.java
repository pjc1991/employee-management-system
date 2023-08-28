package dev.pjc1991.ems.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @Column(name = "region_id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Size(max = 25)
    @Column(name = "region_name", length = 25)
    private String regionName;

}
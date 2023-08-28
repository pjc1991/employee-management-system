package dev.pjc1991.ems.domain.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "countries")
@Getter
public class Country {
    @Id
    @Column(name = "country_id", nullable = false, columnDefinition = "char(2)")
    private String countryId;

    @Size(max = 40)
    @Column(name = "country_name", length = 40)
    private String countryName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;


}
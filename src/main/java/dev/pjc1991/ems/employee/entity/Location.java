package dev.pjc1991.ems.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 40)
    @Column(name = "street_address", length = 40)
    private String streetAddress;

    @Size(max = 12)
    @Column(name = "postal_code", length = 12)
    private String postalCode;

    @Size(max = 30)
    @NotNull
    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Size(max = 25)
    @Column(name = "state_province", length = 25)
    private String stateProvince;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


}
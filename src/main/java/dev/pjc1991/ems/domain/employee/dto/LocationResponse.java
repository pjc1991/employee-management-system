package dev.pjc1991.ems.domain.employee.dto;

import dev.pjc1991.ems.domain.employee.entity.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponse {

    private Integer id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;
    private String countryName;

    public LocationResponse(Location entity) {
        this.id = entity.getId();
        this.streetAddress = entity.getStreetAddress();
        this.postalCode = entity.getPostalCode();
        this.city = entity.getCity();
        this.stateProvince = entity.getStateProvince();
        this.countryId = entity.getCountry().getCountryId();
        this.countryName = entity.getCountry().getCountryName();
    }
}

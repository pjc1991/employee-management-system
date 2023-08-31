package dev.pjc1991.ems.domain.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureRetrofitResponse {
    private String id;
    private String name;
    private String start;
    private String short_description;
}

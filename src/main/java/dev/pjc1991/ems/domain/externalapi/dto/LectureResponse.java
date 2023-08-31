package dev.pjc1991.ems.domain.externalapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class LectureResponse {
    private String id;
    private String name;
    private LocalDateTime start;
    private String description;

    public LectureResponse(LectureRetrofitResponse retrofitResponse) {
        this.id = retrofitResponse.getId();
        this.name = retrofitResponse.getName();
        try {
            this.start = LocalDateTime.parse(retrofitResponse.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        } catch (Exception e) {
            // ignore
        }
        this.description = retrofitResponse.getShort_description();
    }
}

package dev.pjc1991.ems.domain.externalapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureListRetrofitResponse {
    private Pagination pagination;
    private List<LectureRetrofitResponse> results = new ArrayList<>();

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        private int count;
        private int num_pages;
        public static final int PAGE_SIZE = 10;
    }
}

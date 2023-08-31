package dev.pjc1991.ems.domain.externalapi.service;

import dev.pjc1991.ems.domain.externalapi.dto.LectureResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LectureRetrofitResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LecturePageRequest;
import org.springframework.data.domain.Page;

public interface ExternalApiService {
    Page<LectureResponse> getLecturesByPage(LecturePageRequest request);

    LectureResponse getLectureDetail(String id);
}

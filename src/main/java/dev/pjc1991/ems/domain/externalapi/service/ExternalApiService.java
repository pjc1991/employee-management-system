package dev.pjc1991.ems.domain.externalapi.service;

import dev.pjc1991.ems.domain.externalapi.dto.Lecture;
import dev.pjc1991.ems.domain.externalapi.dto.LecturePageRequest;
import org.springframework.data.domain.Page;

public interface ExternalApiService {
    Page<Lecture> getLecturesByPage(LecturePageRequest request);
}

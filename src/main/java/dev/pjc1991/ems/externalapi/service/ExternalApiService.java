package dev.pjc1991.ems.externalapi.service;

import dev.pjc1991.ems.externalapi.dto.Lecture;
import dev.pjc1991.ems.externalapi.dto.LecturePageRequest;
import org.springframework.data.domain.Page;

public interface ExternalApiService {
    Page<Lecture> getLecturesByPage(LecturePageRequest request);
}

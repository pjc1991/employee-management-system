package dev.pjc1991.ems.domain.externalapi.service;

import dev.pjc1991.ems.domain.externalapi.dto.LectureListRetrofitResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LecturePageRequest;
import dev.pjc1991.ems.domain.externalapi.dto.LectureResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExternalApiServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ExternalApiServiceTest.class);
    private static final String TEST_COURSE_ID = "course-v1:ACRCEDU+ACRC03+2023_T1";
    @Autowired
    ExternalApiService externalApiService;

    @Test
    void getLecturesByPage() {
        // given
        LecturePageRequest request = new LecturePageRequest();
        request.setPage(1);

        // when
        Page<LectureResponse> page = externalApiService.getLecturesByPage(request);

        // then
        page.forEach(lectureResponse -> log.info("lectureResponse = {}", lectureResponse));
        assertEquals(1, page.getNumber());
        assertEquals(10, page.getSize());
        assertTrue(page.getTotalElements() > 0);
        assertTrue(page.getTotalPages() > 0);
        assertFalse(page.getContent().isEmpty());
        assertFalse(page.getContent().size() > LectureListRetrofitResponse.Pagination.PAGE_SIZE);


    }

    @Test
    void getLectureDetail() {
        // given
        String id = TEST_COURSE_ID;

        // when
        LectureResponse lectureResponse = externalApiService.getLectureDetail(id);

        // then
        log.info("lectureResponse = {}", lectureResponse);
        assertNotNull(lectureResponse);
        assertEquals(id, lectureResponse.getId());
    }
}
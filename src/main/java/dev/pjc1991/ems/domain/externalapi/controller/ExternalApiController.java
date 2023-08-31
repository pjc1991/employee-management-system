package dev.pjc1991.ems.domain.externalapi.controller;

import dev.pjc1991.ems.domain.externalapi.dto.LecturePageRequest;
import dev.pjc1991.ems.domain.externalapi.dto.LectureResponse;
import dev.pjc1991.ems.domain.externalapi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    @GetMapping("/kmooc")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<LectureResponse> getLectureResponse(LecturePageRequest request) {
        return externalApiService.getLecturesByPage(request);
    }

    @GetMapping("/kmooc/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LectureResponse getLectureDetailResponse(@PathVariable String id) {
        if (id == null) {
            throw new RuntimeException("id is null");
        }
        return externalApiService.getLectureDetail(id);
    }
}

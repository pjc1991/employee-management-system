package dev.pjc1991.ems.domain.externalapi.service.impl;

import dev.pjc1991.ems.domain.externalapi.dto.LectureListRetrofitResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LectureResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LectureRetrofitResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LecturePageRequest;
import dev.pjc1991.ems.domain.externalapi.retrofit.DataServiceRetrofit;
import dev.pjc1991.ems.domain.externalapi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {

    private final DataServiceRetrofit dataServiceRetrofit;

    @Override
    public Page<LectureResponse> getLecturesByPage(LecturePageRequest request) {
        Call<LectureListRetrofitResponse> call = dataServiceRetrofit.getLecturesByPage(convertObjectToMap(request));

        try {
            Response<LectureListRetrofitResponse> response = call.execute();
            LectureListRetrofitResponse body = response.body();
            if (body == null) {
                throw new RuntimeException("body is null");
            }

            if (body.getResults() == null) {
                throw new RuntimeException("results is null");
            }

            return new PageImpl<>(body.getResults().stream().map(LectureResponse::new).toList(), PageRequest.of(request.getPage(), LectureListRetrofitResponse.Pagination.PAGE_SIZE), body.getPagination().getCount());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public LectureResponse getLectureDetail(String id) {
        Call<LectureRetrofitResponse> call = dataServiceRetrofit.getLectureDetail(Map.of("CourseId", id));

        try {
            Response<LectureRetrofitResponse> response = call.execute();
            LectureRetrofitResponse body = response.body();
            if (body == null) {
                throw new RuntimeException("body is null");
            }

            return new LectureResponse(body);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    private Map<String, String> convertObjectToMap(Object object) {
        Map<String, String> map = new HashMap<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(object).toString());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("convertObjectToMap error", e);
        }
        return map;

    }
}

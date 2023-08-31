package dev.pjc1991.ems.domain.externalapi.retrofit;

import dev.pjc1991.ems.domain.externalapi.dto.LectureListRetrofitResponse;
import dev.pjc1991.ems.domain.externalapi.dto.LectureRetrofitResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface DataServiceRetrofit {

    @GET("/B552881/kmooc/courseList")
    Call<LectureListRetrofitResponse> getLecturesByPage(
            @QueryMap Map<String, String> params);

    @GET("/B552881/kmooc/courseDetail")
    Call<LectureRetrofitResponse> getLectureDetail(
            @QueryMap Map<String, String> params);
}

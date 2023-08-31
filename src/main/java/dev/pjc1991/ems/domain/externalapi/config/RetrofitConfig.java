package dev.pjc1991.ems.domain.externalapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pjc1991.ems.domain.externalapi.retrofit.DataServiceRetrofit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Profile("prod")
@Slf4j
@Configuration
public class RetrofitConfig {

    @Value("${kr.go.data.service.key}")
    private String SERVICE_KEY;

    private static final String BASE_URL = "https://apis.data.go.kr";

    @Bean
    public DataServiceRetrofit dataServiceRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String url = chain.request().url().toString();
                    url += url.contains("?") ? "&" : "?";
                    url += "serviceKey=" + SERVICE_KEY;
                    return chain.proceed(chain.request().newBuilder().url(url).build());
                })
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(okHttpClient)
                .build();

        return retrofit.create(DataServiceRetrofit.class);
    }

}

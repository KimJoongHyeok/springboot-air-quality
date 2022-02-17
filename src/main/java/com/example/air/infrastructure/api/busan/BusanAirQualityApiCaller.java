package com.example.air.infrastructure.api.busan;

import com.example.air.application.City;
import com.example.air.application.KoreaAirQualityService;
import com.example.air.application.util.AirQualityGradeUtil;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiDto;
import com.example.air.interfaces.api.dto.AirQualityDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BusanAirQualityApiCaller implements KoreaAirQualityService {
    private final BusanAirQualityApi busanAirQualityApi;

    public BusanAirQualityApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanAirQualityApi.class);
    }

    @Override
    public City getCity() {
        return City.busan;
    }

    @Override
    public AirQualityDto.GetAirQualityInfo getAirQualityInfo() {
        try {
            var call = busanAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResult().isSuccess()) {
                log.info(response.toString());
                return convert(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. header=" + response.getResult().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }
    //JSON으로 받아온 response를 AirQualityDTO에 맞게 변환

    private AirQualityDto.GetAirQualityInfo convert(BusanAirQualityApiDto.GetAirQualityResponse response) {
        var items = response.getResult().getItems();
        var cityPm10Avg = averagePm10(items);
        var cityPm10AvgGrade = AirQualityGradeUtil.getPm10Grade(cityPm10Avg);
        var districtList = convert(items);

        return AirQualityDto.GetAirQualityInfo.builder()
                .city(City.busan.getDescription())
                .cityPm10Avg(cityPm10Avg)
                .cityPm10AvgGrade(cityPm10AvgGrade)
                .districtList(districtList)
                .build();
    }

    private Double averagePm10(List<BusanAirQualityApiDto.Item> items) {
        return items.stream()
                .mapToInt(BusanAirQualityApiDto.Item::getPm10)
                .average()
                .orElse(Double.NaN);
    }

    //오버로딩
    //JSON 형태의 List<SeoulAirQualityApiDto.Row>에 들어있는 row를 List<AirQualityDto.DistrictAirQualityInfo>로 변환
    private List<AirQualityDto.DistrictAirQualityInfo> convert(List<BusanAirQualityApiDto.Item> items) {
        return items.stream()
                .map(row -> new AirQualityDto.DistrictAirQualityInfo(
                        row.getSite(),
                        row.getPm25(),
                        row.getPm10(),
                        row.getO3(),
                        row.getNo2(),
                        row.getCo(),
                        row.getSo2())
                )
                .collect(Collectors.toList()); //Collectors.toList() : 모든 Stream elements를 List나 Set instance로 변경하는 메서드
    }


}

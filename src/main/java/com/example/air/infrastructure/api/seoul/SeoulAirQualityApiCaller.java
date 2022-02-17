package com.example.air.infrastructure.api.seoul;

import com.example.air.application.City;
import com.example.air.application.KoreaAirQualityService;
import com.example.air.application.util.AirQualityGradeUtil;
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
public class SeoulAirQualityApiCaller implements KoreaAirQualityService {
    private final SeoulAirQualityApi seoulAirQualityApi;

    public SeoulAirQualityApiCaller(@Value("${api.seoul.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.seoulAirQualityApi = retrofit.create(SeoulAirQualityApi.class);
    }

    @Override
    public City getCity() {
        return City.seoul;
    }

    @Override
    public AirQualityDto.GetAirQualityInfo getAirQualityInfo() {
        try {
            var call = seoulAirQualityApi.getAirQuality();
            var response = call.execute().body(); //실제 실행문

            if (response == null || response.getResponse() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResponse().isSuccess()) {    //호출해서 값이 정상적으로 호출됐다면, 원래는 response.getSeoulResult().getRow()... 이런식으로 해도되지만 가독성을 위해
                log.info(response.toString());
                return convert(response); //convert 추가
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. result=" + response.getResponse().getResult());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    //JSON으로 받아온 response를 AirQualityDTO에 맞게 변환
    private AirQualityDto.GetAirQualityInfo convert(SeoulAirQualityApiDto.GetAirQualityResponse response) {
        var rows = response.getResponse().getRow();
        var cityPm10Avg = averagePm10(rows);
        var cityPm10AvgGrade = AirQualityGradeUtil.getPm10Grade(cityPm10Avg);
        var districtList = convert(rows);

        return AirQualityDto.GetAirQualityInfo.builder()
                .city(City.seoul.getDescription())
                .cityPm10Avg(cityPm10Avg)
                .cityPm10AvgGrade(cityPm10AvgGrade)
                .districtList(districtList)
                .build();
    }

    private Double averagePm10(List<SeoulAirQualityApiDto.Row> rows) {
        return rows.stream()
                .mapToInt(SeoulAirQualityApiDto.Row::getPm10)
                .average()
                .orElse(Double.NaN);
    }

    //오버로딩
    //JSON 형태의 List<SeoulAirQualityApiDto.Row>에 들어있는 row를 List<AirQualityDto.DistrictAirQualityInfo>로 변환
    private List<AirQualityDto.DistrictAirQualityInfo> convert(List<SeoulAirQualityApiDto.Row> rows) {
        return rows.stream()
                .map(row -> new AirQualityDto.DistrictAirQualityInfo(
                        row.getDistrict(),
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

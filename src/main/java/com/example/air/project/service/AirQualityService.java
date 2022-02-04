package com.example.air.project.service;

import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirQualityService {

    @Autowired
    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityService(SeoulAirQualityApiCaller seoulAirQualityApiCaller) {
        this.seoulAirQualityApiCaller = seoulAirQualityApiCaller;
    }

    public SeoulAirQualityApiDto.GetAirQualityResponse getAirQuality(String city, String district) {
        if (city.equals("seoul")) {
            var response = seoulAirQualityApiCaller.getAirQuality();
            if (district != null) {
                for (int i = 0; i < response.getSeoulResult().getList_total_count(); i++) {
                    if (response.getSeoulResult().getRow().get(i).getDistrict().equals(district)) {
                        var response2 = response.getSeoulResult().getRow().get(i);
                    }
                }
            }
            log.info(response.toString());
            log.info(response.getSeoulResult().getRow().get(0).getDistrict());
            return response;
        }

        throw new RuntimeException("seoul 만 가능합니당");
    }
}

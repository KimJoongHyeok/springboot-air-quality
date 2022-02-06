package com.example.air.application;

import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import com.example.air.interfaces.api.dto.AirQualityDto;
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

    public AirQualityDto.GetAirQualityInfo getAirQuality(City city, String district) {
        if (city == City.seoul) {
            var airQualityInfo = seoulAirQualityApiCaller.getAirQuality();
            if (district != null) {
                return airQualityInfo.searchByDistrict(district);
            }
            log.info(airQualityInfo.toString());
            return airQualityInfo;
        }

        throw new RuntimeException("seoul 만 가능합니당");
    }
}

package com.example.air.application;

import com.example.air.interfaces.api.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirQualityService {
    private final KoreaAirQualityServiceFactory koreaAirQualityServiceFactory;

    @Cacheable("city")
    //@CachePut("city")
    public AirQualityDto.GetAirQualityInfo getAirQualityInfo(City city, String district) {
        KoreaAirQualityService service = koreaAirQualityServiceFactory.getService(city);

        var airQualityInfo = service.getAirQualityInfo();
        if (district.equals("all") == false) {
            return airQualityInfo.searchByDistrict(district);
        }
        log.info(airQualityInfo + "를 조회");
        return airQualityInfo;
    }
}

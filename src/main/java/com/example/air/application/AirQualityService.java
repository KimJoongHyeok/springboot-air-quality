package com.example.air.application;

import com.example.air.infrastructure.api.busan.BusanAirQualityApiCaller;
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
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;

    public AirQualityService(SeoulAirQualityApiCaller seoulAirQualityApiCaller, BusanAirQualityApiCaller busanAirQualityApiCaller) {
        this.seoulAirQualityApiCaller = seoulAirQualityApiCaller;
        this.busanAirQualityApiCaller = busanAirQualityApiCaller;
    }

    public AirQualityDto.GetAirQualityInfo getAirQuality(City city, String district) {
        var airQualityInfo = createApiCaller(city);
        if (district != null) {
            return airQualityInfo.searchByDistrict(district);
        }
        log.info(airQualityInfo.toString());
        return airQualityInfo;
    }

    public AirQualityDto.GetAirQualityInfo createApiCaller(City city) {
        AirQualityDto.GetAirQualityInfo airQualityInfo = null;
        if (city == City.seoul) {
            airQualityInfo = seoulAirQualityApiCaller.getAirQuality();
        } else if (city == City.busan) {
            airQualityInfo = busanAirQualityApiCaller.getAirQuality();
        } else {
            throw new RuntimeException("입력된 도시에 해당하는 정보가 없습니다");
        }
        return airQualityInfo;
    }
}

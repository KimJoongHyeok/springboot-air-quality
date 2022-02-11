package com.example.air.application;

import com.example.air.interfaces.api.dto.AirQualityDto;
import org.springframework.beans.factory.annotation.Autowired;

public class AirApiFactory {

//    @Autowired
//    private final AirQualityService seoulAirQualityApiCaller;
//    private final
//
//    public AirApiFactory(AirQualityService seoulAirQualityApiCaller) {
//        this.seoulAirQualityApiCaller = seoulAirQualityApiCaller;
//    }
//
//    public AirQualityDto.GetAirQualityInfo createApiCaller(City city) {
//        AirQualityDto.GetAirQualityInfo airQualityInfo = null;
//        if (city == City.seoul) {
//            airQualityInfo = seoulAirQualityApiCaller.getAirQuality();
//        } else if (city == City.busan) {
//            airQualityInfo = busanAirQualityApiCaller.getAirQuality();
//        } else {
//            throw new RuntimeException("입력된 도시에 해당하는 정보가 없습니다");
//        }
//        return airQualityInfo;
//    }
}

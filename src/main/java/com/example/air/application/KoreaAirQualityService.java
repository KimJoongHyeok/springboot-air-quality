package com.example.air.application;

import com.example.air.interfaces.api.dto.AirQualityDto;

public interface KoreaAirQualityService { //팩토리 메서드 인터페이스
    City getCity();

    AirQualityDto.GetAirQualityInfo getAirQualityInfo();
}

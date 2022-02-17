package com.example.air.application;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KoreaAirQualityServiceFactory { //팩토리 메서드 를 위한 클래스, 팩토리 메서드 공부..
    private final Map<City, KoreaAirQualityService> serviceMap = new HashMap<>();

    /**
     * 생성자를 통해 KoreaAirQualityService 를 상속받은 서비스를 key : value 형태로 저장함
     * { seoul : SeoulAirQualityApiCaller, busan : BusanAirQualityApiCaller }
     */
    public KoreaAirQualityServiceFactory(List<KoreaAirQualityService> services) {
        for (var service : services) {
            serviceMap.put(service.getCity(), service);
        }
    }

    public KoreaAirQualityService getService(City city) {
        return Optional.of(serviceMap.get(city))
                .orElseThrow(() -> new RuntimeException("대기질 정보를 조회할 수 없는 시/도 입니다."));
    }
}

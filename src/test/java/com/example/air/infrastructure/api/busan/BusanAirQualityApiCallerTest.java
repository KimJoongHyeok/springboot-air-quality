package com.example.air.infrastructure.api.busan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BusanAirQualityApiCallerTest {

    @Autowired
    BusanAirQualityApiCaller busanAirQualityApiCaller;

    @Test
    public void 부산_대기질_조회_API_호출() {
        // when
        var response = busanAirQualityApiCaller.getAirQualityInfo();

        // then
        assertNotNull(response);
    }

}
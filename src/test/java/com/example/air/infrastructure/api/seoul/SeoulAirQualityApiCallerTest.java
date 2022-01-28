package com.example.air.infrastructure.api.seoul;

import com.example.air.infrastructure.api.busan.BusanAirQualityApiCaller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SeoulAirQualityApiCallerTest {

    @Autowired
    SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    @Test
    public void 서울_대기질_조회_API_호출(){
        //given

        //when
        var response = seoulAirQualityApiCaller.getAirQuality();

        //then
        assertNotNull(response);
    }
}

package com.example.air.interfaces.api;

import com.example.air.application.AirQualityService;
import com.example.air.application.City;
import com.example.air.interfaces.api.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/air-quality")
public class AirQualityController {
    private final AirQualityService airQualityService;

    @GetMapping("/{city}")
    public AirQualityDto.GetAirQualityInfo getAirQualityInfo(@PathVariable("city") City city,
                                                             @RequestParam(required = false, defaultValue = "all") String district) {
        log.info(airQualityService.getAirQualityInfo(city, district).toString() + "를 출력");
        return airQualityService.getAirQualityInfo(city, district);
    }
}

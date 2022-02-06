package com.example.air.interfaces.api;

import com.example.air.application.City;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiDto;
import com.example.air.application.AirQualityService;
import com.example.air.interfaces.api.dto.AirQualityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/air-quality")
public class AirQualityController {

    @Autowired
    private final AirQualityService airQualityService;

    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }

    @GetMapping("/{city}")
    public AirQualityDto.GetAirQualityInfo airQuality(@PathVariable("city") City city,
                                                      @RequestParam(required = false) String district) {
        return airQualityService.getAirQuality(city, district);
    }

}

package com.example.air.project.controller;

import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiDto;
import com.example.air.project.service.AirQualityService;
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
    public SeoulAirQualityApiDto.GetAirQualityResponse airQuality(@PathVariable("city") String city, @RequestParam(required = false) String district) {
        return airQualityService.getAirQuality(city, district);
    }

}

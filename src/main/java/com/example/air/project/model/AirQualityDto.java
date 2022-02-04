package com.example.air.project.model;

import lombok.*;

import java.util.List;

@Data
public class AirQualityDto {

    @Getter
    @Setter
    @ToString
    public static class getAirQualityResponse{
        private String city;
        private Double cityPm10Avg;
        private AirQualityGrade airQualityGrade;
        private List<result> list;

    }

    @Getter
    @ToString
    public static class result {
        private String district;
        private String measureDate;
        private int pm25;
        private int pm10;
        private double o3;
        private double no2;
        private double co;
        private double so2;
        private AirQualityGrade pm25Grade;
        private AirQualityGrade pm10Grade;
        private AirQualityGrade o3Grade;
        private AirQualityGrade no2Grade;
        private AirQualityGrade coGrade;
        private AirQualityGrade so2Grade;
    }
}


package com.example.air.infrastructure.api.seoul;

import com.example.air.infrastructure.api.busan.BusanAirQualityApiDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

public class SeoulAirQualityApiDto {

    @Getter
    @Setter
    @ToString
    public static class GetAirQualityResponse {
        @JsonProperty("RealtimeCityAir")
        private SeoulAirQualityApiDto.SeoulResult seoulResult;
    }

    @Getter
    @Setter
    @ToString
    public static class SeoulResult {

        private Integer list_total_count;
        @JsonProperty("RESULT")
        private Result result;
        @JsonProperty("row")
        private List<SeoulAirQualityApiDto.row> row;
        private Integer START_INDEX;
        private Integer END_INDEX;


        public boolean isSuccess() {
            if (Objects.equals(result.getCode(), "INFO-000")) {
                return true;
            }
            return false;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Result {
        @JsonProperty("CODE")
        private String code;
        @JsonProperty("MESSAGE")
        private String message;
    }

    @Getter
    @Setter
    @ToString
    public static class row {
        @JsonProperty("MSRSTE_NM")
        private String district;
        @JsonProperty("MSRDT")
        private String measurementTime;
        @JsonProperty("PM25")
        private int pm25;
        @JsonProperty("PM10")
        private int pm10;
        @JsonProperty("O3")
        private double o3;
        @JsonProperty("NO2")
        private double no2;
        @JsonProperty("CO")
        private double co;
        @JsonProperty("SO2")
        private double so2;
    }
}

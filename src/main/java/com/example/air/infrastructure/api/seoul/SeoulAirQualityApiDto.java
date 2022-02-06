package com.example.air.infrastructure.api.seoul;

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
        private Response response;
    }

    @Getter
    @Setter
    @ToString
    public static class Response {

        private int list_total_count;
        @JsonProperty("RESULT")
        private Result result;
        @JsonProperty("row")
        private List<Row> row;

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
    public static class Row {
        @JsonProperty("MSRRNG_NM") //권역명
        private String area;
        @JsonProperty("MSRSTE_NM") //측정소명
        private String district;
        @JsonProperty("MSRDT")
        private String measurementTime;
        @JsonProperty("PM25")
        private Integer pm25;
        @JsonProperty("PM10")
        private Integer pm10;
        @JsonProperty("O3")
        private Double o3;
        @JsonProperty("NO2")
        private Double no2;
        @JsonProperty("CO")
        private Double co;
        @JsonProperty("SO2")
        private Double so2;
    }
}

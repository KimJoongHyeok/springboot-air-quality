package com.example.air.infrastructure.api.busan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

public class BusanAirQualityApiDto {
    @Getter
    @Setter
    @ToString
    public static class GetAirQualityResponse {
        @JsonProperty("getAirQualityInfoClassifiedByStation")
        private Result result;
    }

    @Getter
    @Setter
    @ToString
    public static class Result {
        private Header header;
        @JsonProperty("item")
        private List<Item> items;
        private Integer numOfRows;
        private Integer pageNo;
        private Integer totalCount;

        public boolean isSuccess() {
            if (Objects.equals(header.getCode(), "00")) {
                return true;
            }
            return false;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Header {
        private String code;
        private String message;
    }

    @Getter
    @Setter
    @ToString
    public static class Item {
        private String site;
        private String areaIndex;
        @JsonProperty("controlnumber")
        private String measurementTime;
        private String repItem;
        private String repVal;
        private Double so2;
        private Double no2;
        private Double o3;
        private Double co;
        private Integer pm25;
        private Integer pm10;
    }
}

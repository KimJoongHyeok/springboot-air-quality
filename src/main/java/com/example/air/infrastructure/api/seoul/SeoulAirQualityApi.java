package com.example.air.infrastructure.api.seoul;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeoulAirQualityApi {
    String serviceKey = "536c58687267757531334b46554b41";

    @GET(serviceKey + "/json/RealtimeCityAir/1/25/")

    Call<SeoulAirQualityApiDto.GetAirQualityResponse> getAirQuality();
}

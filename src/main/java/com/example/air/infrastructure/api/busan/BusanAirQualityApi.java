package com.example.air.infrastructure.api.busan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BusanAirQualityApi {
    String serviceKey = "HhjTuva6pPQzIIVVifQKj8ASob8AhfT9B6AjiqLmeMZzyZm8uvV8Va759bBMwpj2ULo5TWDmreOTFZJRz4T2Yw==";

    @GET("AirQualityInfoService/getAirQualityInfoClassifiedByStation?serviceKey=" + serviceKey +
            "&resultType=json&pageNo=1&numOfRows=33")
    Call<BusanAirQualityApiDto.GetAirQualityResponse> getAirQuality();
}

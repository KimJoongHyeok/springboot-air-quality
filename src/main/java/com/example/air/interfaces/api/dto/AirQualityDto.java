package com.example.air.interfaces.api.dto;

import com.example.air.application.AirQualityGrade;
import com.example.air.application.util.AirQualityGradeUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;


public class AirQualityDto {

    @Getter
    @Builder
    public static class GetAirQualityInfo{
        private String city;
        private Double cityPm10Avg;
        private AirQualityGrade cityPm10AvgGrade;
        private List<DistrictAirQualityInfo> districtList;

        public GetAirQualityInfo searchByDistrict(String district){
            //메서드호출시 검사를 했지만 한번더 검사
            if(district == null){
                return this;
            }

            //district에 대한 정보를 조회,list형이 아닌 DistrictAirQualityInfo형으로 반환
            var searchDistrictInfo = searchDistrictAirQualityInfo(district);

            //districtList값은 List<DistrictAirQualityInfo> 형이기 때문에 변환,하나의 값만 나올꺼기 때문에 singletionList
            //메모리 절약을 위해서 요소가 없거나(empty) 하나인 경우에는 Collections.emptyList() 또는 Collections.singletonList()를 사용
            districtList = Collections.singletonList(searchDistrictInfo);
            return this;
        }

        public DistrictAirQualityInfo searchDistrictAirQualityInfo(String district){
            return districtList.stream()
                    //if문 같은 기능,
                    // districtList가 List<DistrictAirQualityInfo>형이고 그 값을 stream 한값값
                    // districtAirQualityInfo = AirQualityDTO.DistrictAirQualityInfo districtAirQualityInfo
                    .filter(districtAirQualityInfo -> districtAirQualityInfo.getDistrict().equals(district))
                    //걸러진 값들중에 첫번째 값
                    .findFirst()
                    //람다식(매개변수) -> 실행문, 값이 없다면 예외처리
                    .orElseThrow(() -> new IllegalArgumentException(district + "에 해당하는 자치구가 존재하지 않습니다"));
        }
    }

    @Getter
    public static class DistrictAirQualityInfo {
        private final String district;
        private final Integer pm25;
        private final Integer pm10;
        private final Double o3;
        private final Double no2;
        private final Double co;
        private final Double so2;
        private AirQualityGrade pm25Grade;
        private AirQualityGrade pm10Grade;
        private AirQualityGrade o3Grade;
        private AirQualityGrade no2Grade;
        private AirQualityGrade coGrade;
        private AirQualityGrade so2Grade;
        
        //Builder 패턴을 사용하지 않고 생성자를 사용한 이유는 매개변수로 값들을 받아서 Util에서 처리하면 미세먼지값과 등급을 한번에 처리할 수 있기 때문
        public DistrictAirQualityInfo(String district, Integer pm25, Integer pm10, Double o3, Double no2, Double co, Double so2) {
            this.district = district;
            this.pm25 = pm25;
            this.pm10 = pm10;
            this.o3 = o3;
            this.no2 = no2;
            this.co = co;
            this.so2 = so2;
            this.pm25Grade = AirQualityGradeUtil.getPm25Grade(pm25);
            this.pm10Grade = AirQualityGradeUtil.getPm10Grade(Double.valueOf(pm10));
            this.o3Grade = AirQualityGradeUtil.getO3Grade(o3);
            this.no2Grade = AirQualityGradeUtil.getNo2Grade(no2);
            this.coGrade = AirQualityGradeUtil.getCoGrade(co);
            this.so2Grade = AirQualityGradeUtil.getSo2Grade(so2);
            
        }
    }
}


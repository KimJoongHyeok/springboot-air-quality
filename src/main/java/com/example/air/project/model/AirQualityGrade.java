package com.example.air.project.model;

public class AirQualityGrade {

    public String getPm25Grade(int pm25){
        if(pm25 <= 30){
            return "좋음";
        }else if(pm25 <= 60){
            return "보통";
        }else if(pm25 <= 90){
            return "나쁨";
        }else
            return "매우나쁨";
    }


}

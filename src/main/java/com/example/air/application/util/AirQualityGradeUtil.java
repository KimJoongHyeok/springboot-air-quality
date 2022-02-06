package com.example.air.application.util;

import com.example.air.application.AirQualityGrade;
import static com.example.air.application.AirQualityGrade.*;

public class AirQualityGradeUtil {

    //Util class 에 빈 생성자를 만드는 이유는 DI할때 자동적으로 Autowired하는 것을 막아 메모리를 효율적으로 관리해 클린코드 작성을 위해 작성
    public AirQualityGradeUtil(){}

    public static AirQualityGrade getPm25Grade(Integer pm25){
        if(pm25 <= 15) return 좋음;
        else if(pm25 <= 35) return 보통;
        else if(pm25 <= 75) return 나쁨;
        else return 매우나쁨;
    }

    public static AirQualityGrade getPm10Grade(Double pm10){
        if(pm10 <= 30) return 좋음;
        else if(pm10 <= 80) return 보통;
        else if(pm10 <= 150) return 나쁨;
        else return 매우나쁨;
    }

    public static AirQualityGrade getO3Grade(Double o3){
        if(o3 <= 0.030) return 좋음;
        else if(o3 <= 0.090) return 보통;
        else if(o3 <= 0.150) return 나쁨;
        else return 매우나쁨;
    }

    public static AirQualityGrade getNo2Grade(Double no2){
        if(no2 <= 0.030) return 좋음;
        else if(no2 <= 0.060) return 보통;
        else if(no2 <= 0.200) return 나쁨;
        else return 매우나쁨;
    }

    public static AirQualityGrade getCoGrade(Double co){
        if(co <= 2) return 좋음;
        else if(co <= 9) return 보통;
        else if(co <= 15) return 나쁨;
        else return 매우나쁨;
    }

    public static AirQualityGrade getSo2Grade(Double so2){
        if(so2 <= 0.020) return 좋음;
        else if(so2 <= 0.050) return 보통;
        else if(so2 <= 0.150) return 나쁨;
        else return 매우나쁨;
    }
}

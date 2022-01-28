# 전국 대기질 정보 서비스 
# Springboot - RestAPI 명세서부터 만들어보기 

### Request

- URL

  - GET /api/v1/air-quality/{city}/{district}

#### Parameter

| Name | Type | Desciption | Required |
| --- | --- | --- | --- |
| city | String | 도시명 | O |
| district | String | 측정소명 | X |

### Response

| Name | Type | Description |
| --- | --- | --- |
| district | String | 측정소명, 구 |
| measuredDate | String | 측정일시 |
| pm25 | int | 초미세먼지(㎍/㎥) |
| pm10 | int | 미세먼지(㎍/㎥) |
| o3 | double | 오존(ppm) |
| no2 | double | 이산화질소농도(ppm) |
| co | double | 일산화탄소농도(ppm) |
| so2 | double | 아황산가스농도(ppm) |
| pm25Grade | String | 초미세먼지 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |
| pm10Grade | String | 미세먼지 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |
| o3Grade | String | 오존 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |
| no2Grade | String | 이산화질소농도 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |
| coGrade | String | 일산화탄소농도 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |
| so2Grade | String | 아황산가스농도 등급정보(ex 0~30 : 좋음, 31~80 : 보통 , 81 ~ 150 : 나쁨,) 151 ~ : 매우나쁨 , - : 점검중) |

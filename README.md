# 전국 대기질 정보 서비스 Rest api 명세서 

## Request

- URL

  - GET /api/v1/air-quality/{city}/{district}

### Parameter

| Name | Type | Desciption | Required |
| --- | --- | --- | --- |
| city | String | 조회하고 자하는 도시명 *seoul : 서울시 *busan : 부산시 | O |
| district | String | 조회하고 자하는 자치구(측정소명) ex) 노원구, 성북구, 구로구... | X |

<br>

## Response
### Parameters 

| Name | Child | Tags | Description | Required |
| :---: | :---: | :---: | :---: | :---: |
| sido | - | String | 시/도 명 ex) 서울시, 부산시  | O |
| sidoPm10Avg | - | Double | 시/도 평균 미세먼지 수치 | X |
| sidoPm10AvgGrade | - | String | 시/도 평균 미세먼지 등급 | X |
| districtList | - | Array | 자치구 대기질 정보 리스트 | X |
| - | district | String | 자치구 명 ex) 노원구, 성북구, 구로구, ... | X |
| - | pm10 | Integer | 미세먼지 수치  |X
| - | pm10Grade | String | 미세먼지 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |
| - | pm25 | Integer | 초미세먼지 수치 | X |
| - | pm25Grade | String | 초미세먼지 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |
| - | o3 | Double | 오존 수치 | X |
| - | o3Grade | String | 오존 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |
| - | no2 | Double | 이산화질소 수치 | X |
| - | no2Grade | String | 이산화질소 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |
| - | co | Double | 일산화탄소 수치 | X |
| - | coGrade | String | 일산화탄소 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |
| - | so2 | Double | 아황산가스 수치 | X |
| - | so2Grade | String | 아황산가스 등급 ex) 좋음, 보통, 나쁨, 매우나쁨 | X |

## Sample
### Request

  - GET http://localhost:8080/api/v1/air-quality/seoul?district=중구

    
### Response
```json
{
  "sido": "서울시",
  "sidoPm10Avg": 7.16,
  "sidoPm10AvgGrade": "좋음",
  "guList": [
    {
      "gu": "중구",
      "pm10": 5,
      "pm25": 2,
      "o3": 0.02,
      "no2": 0.02,
      "co": 0.3,
      "so2": 0.003,
      "pm10Grade": "좋음",
      "pm25Grade": "좋음",
      "o3Grade": "좋음",
      "no2Grade": "좋음",
      "coGrade": "좋음",
      "so2Grade": "좋음"
    }
  ]
}
```

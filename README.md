# LifeStudio

<!-- ABOUT THE PROJECT -->
## About The Project

파편화된 사진관 정보들을 하나의 플랫폼에 모두 모은 인생사진관
소개영상 : https://youtu.be/fRLa7Q-azfU

### 주요 기능

#### 로그인, 회원가입
- 이메일 인증 기반 회원가입을 진행합니다.
- 이메일, 패스워드를 통해 로그인을 합니다
- 관리자, 사진관, 일반유저 계정으로 구분됩니다.

#### 정보 수정
- 개인 정보 수정 페이지에서 유저 정보를 수정 가능합니다.
- 사진관 계정으로 로그인 시 사진관 정보 수정 페이지에서 사진관 정보를 수정 가능합니다

#### 사진관 정보 등록
- 관리자 계정으로 사진관 정보를 등록합니다
- 사진관 정보, 사진, 연락처, 사업장 정보등을 입력합니다
- 연락처를 바탕으로 사진관 계정을 생성합니다

#### 위치 기반 사진관 찾기
- 현재 위치 및 선택한 사진관 유형을 기반으로 사진관을 보여줍니다. 
- 사진관 대표 사진, 이름, 평균 별점, 위치, 태그를 보여줍니다.

#### 스타일북
- 스타일에 따른 사진을 모아서 보여줍니다.
- 사진관 유형에 맞는 사진을 랜덤으로 보여줍니다
- 사진에 좋아요를 눌러 찜한 사진 페이지에서 모아 볼 수 있습니다.

#### 사진관 소개
- 사진관 개별페이지에서 사진관에 대한 자세한 정보를 보여줍니다.
- 대표사진, 사진관 설명, 리뷰 등을 보여줍니다.
- 사진관 예약버튼을 통해 예약페이지로 이동 가능합니다.

#### 사진관 예약
- 일반 유저는 예약 페이지에서 사진관 방문 일정을 예약할 수 있습니다
- 날짜 및 시간, 사진 옵션, 요청사항등을 선택합니다
- 사진관 관리 계정은 예약 승인 페이지에서 유저들의 예약 내역을 볼 수 있고 승인 가능합니다.

### 사용 기술

- Frontend - React
- Backend - Spring
- DataBase - MySQL


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

이 프로젝트의 패키지들은 Gradle을 통해 관리되고 있습니다. 

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/HYLifeStudio/LifeStudio_Backend
   ```
2. Enter your Environment Variable in src/main/java/resources/application.yml
   
   ```sh
   spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: $DatabaseURL
      username: admin
      password: $Password
   pa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        default_batch_fetch_size: 1000

   mail:
    host: smtp.gmail.com
    port: 587
    username: $EmailName
    password: $EmailPassword
    properties:
      mail:
        smtp:
          starttls:
            enable: true
            required: true
          auth: true
   logging:
    level:
      com.amazonaws.util.EC2MetadataUtils: error
      org.hibernate.SQL: debug
   cloud:
     aws:
      credentials:
        access-key: $AccessKey
        secret-key: $SecretKey
      region:
        static: us-east-2
      stack:
        auto: false
      s3:
        bucket: lifestudio
   ```
   
3. Build

   ```sh
   ./gradlew clean build
   ```
4. Run Server

   ```sh
   java -jar JarName.jar
   ```


## Explore Rest APIs

### API 정리

### Auth

| Method | Url | Decription | Authority | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | 회원가입 | ALL |
| POST   | /api/auth/signin | 로그인 | ALL |
| POST   | /api/auth/emailsend | 인증 이메일 전송 | ALL |
| POST   | /api/auth/emailverification | 이메일 인증 확인 | ALL |

### Users

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/me |로그인 된 유저 정보 조회 | ALL |
| GET    | /api/users | 모든 유저 정보 조회 | ALL |
| GET    | /api/users/{id} | 특정 유저 정보 조회 | ALL |
| POST   | /api/users | 관리자, 사진관 계정 추가 | ADMIN |
| PUT    | /api/users/{id} | 유저 정보 수정 | USER |
| DELETE | /api/users/{id} | 유저 정보 삭제 | ADMIN |

### Studios

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/studios | 모든 사진관 정보 조회 | ALL |
| GET    | /api/studios/{id} | 특정 사진관 정보 조회 | ALL |
| GET    | /api/studios?cityDistrict&studioType | 지역,유형에 맞는 사진관 정보 조회 | ALL |
| POST   | /api/studios | 사진관 정보 추가 | ADMIN |
| PUT    | /api/studios/{id} | 사진관 정보 수정 | STUDIO |
| DELETE | /api/studios/{id} | 사진관 정보 삭제 | ADMIN |

### Reservations

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/reservations      | 모든 예약 정보 조회 | USER |
| GET    | /api/reservations/{id} | 특정 예약 정보 조회 | USER |
| POST   | /api/reservations      | 예약 정보 추가| USER |
| PUT    | /api/reservations/{id} | 예약 정보 수정 | USER |
| DELETE | /api/reservations/{id} | 예약 정보 삭제 | USER |


### Reviews

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/reviews | 모든 리뷰 정보 조회 | ALL |
| GET    | /api/reviews/{id} | 특정 리뷰 정보 조회 | USER |
| POST   | /api/reviews | 리뷰 정보 추가 | USER |
| PUT    | /api/reviews/{id} | 리뷰 정보 수정 | USER |
| DELETE | /api/reviews/{id} | 리뷰 정보 삭제 | USER |

### Photos

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/photos | 모든 사진 정보 조회 | ALL |
| GET    | /api/photos/{id} | 특정 사진 정보 조회 | ALL |
| GET    | /api/photos?studioType | 스튜디오 유형에 맞는 사진 정보 조회| ALL |
| POST   | /api/photos | 사진 정보 추가 | STUDIO |
| POST   | /api/upload | 사진 업로드 | STUDIO |
| PUT    | /api/photos/{id} | 사진 정보 수정 | STUDIO |
| DELETE | /api/photos/{id} | 사진 정보 삭제 | STUDIO |

### Likes

| Method | Url | Description | Authority |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/likes | 모든 좋아요 정보 조회 | USER |
| GET    | /api/likes/{id} | 특정 좋아요 정보 조회 | USER |
| GET    | /api/likes?userId&photoId | 사진,유저에 해당하는 좋아요 정보 조회 | USER |
| POST   | /api/likes | 좋아요 정보 추가 | USER |
| PUT    | /api/likes/{id} | 좋아요 정보 수정 | USER |
| DELETE | /api/likes/{id} | 좋아요 정보 삭제 | USER |

### API 문서

https://documenter.getpostman.com/view/15879468/TzsZsUEd




<!-- CONTRIBUTING -->
## Contributing
- 기획 및 디자인 - 조민주 
- Frontend - 안경록, 최수현
- Backend - 윤승권

<!-- CONTACT -->
## Contact

Yoon Seung Gwon - zxcvb5434@likelion.org

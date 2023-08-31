# 직원 관리 시스템 개발 예시

## 요약

Oracle DB 의 예시 테이블을 이용하여 직원 관리 시스템을 개발한 예시입니다. 

## 프로젝트 기능 

- 직원 정보 조회
- 직원 이력 정보 조회
- 부서 정보 조회 
- 위치 정보 조회
- 부서 급여 인상
- 직원 정보 수정
- 강좌 정보 검색
- 강좌 정보 단건 조회

## 사용하는 기술

- Java 17
- Spring Boot 
- Spring Data JPA
- QueryDSL
- Spring Security
- JUnit 5
- MySQL
- Docker (Local)
- AWS EC2
- AWS RDS

## 프로젝트 실행 방법

로컬에서 이 프로젝트를 실행하려면 다음의 순서로 진행합니다. 

1. 프로젝트를 클론합니다.

```shell
git clone https://github.com/pjc1991/employee-management-system.git &&
cd employee-management-system
```

2. 도커로 MySQL 컨테이너를 실행합니다.

```shell
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_USER=hr -e MYSQL_PASSWORD=secret -e MYSQL_DATABASE=hr --name mysql mysql:8.1
```

2. 프로젝트를 빌드합니다.

```shell
chmod +x gradlew &&
./gradlew clean build
```

3. 어플리케이션을 실행합니다.
    
```shell
./gradlew bootRun
```

## 배포 주소 

- http://52.10.69.37:8080/

## API 명세 

### 직원 정보 조회

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/employee/101
```

- Method: GET
- URI: /employee/{id}
- Path Variable:
  - id: 직원 ID

#### 응답

```json
{
    "id": 101,
    "firstName": "Neena",
    "lastName": "Kochhar",
    "email": "NKOCHHAR",
    "phoneNumber": "515.123.4568",
    "hireDate": "1989-09-21",
    "jobId": "AD_VP",
    "jobTitle": "Administration Vice President",
    "salary": 17000.00,
    "commissionPct": null,
    "managerId": 100,
    "managerName": "Steven King",
    "departmentId": 90,
    "departmentName": "Executive"
}
```

- id: 직원 ID
- firstName: 이름
- lastName: 성
- email: 이메일
- phoneNumber: 전화번호
- hireDate: 입사일
- jobId: 직무 ID
- jobTitle: 직무명
- salary: 급여
- commissionPct: 커미션
- managerId: 관리자 ID
- managerName: 관리자 이름
- departmentId: 부서 ID
- departmentName: 부서명

### 직원 이력 정보 조회

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/employee/101/history
```

- Method: GET
- URI: /employee/{id}/history
- Path Variable:
  - id: 직원 ID

#### 응답

```json
{
    "content": [
        {
            "id": 2,
            "employeeId": 101,
            "employeeName": "Neena Kochhar",
            "jobId": "AC_ACCOUNT",
            "jobTitle": "Public Accountant",
            "startDate": "1989-09-21",
            "endDate": "1993-10-27",
            "departmentId": 110,
            "departmentName": "Accounting"
        },
        {
            "id": 3,
            "employeeId": 101,
            "employeeName": "Neena Kochhar",
            "jobId": "AC_MGR",
            "jobTitle": "Accounting Manager",
            "startDate": "1993-10-28",
            "endDate": "1997-03-15",
            "departmentId": 110,
            "departmentName": "Accounting"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "employeeId": 101,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
```

- content: 직원 이력 정보 목록
  - id: 직원 이력 ID
  - employeeId: 직원 ID
  - employeeName: 직원 이름
  - jobId: 직무 ID
  - jobTitle: 직무명
  - startDate: 시작일
  - endDate: 종료일
  - departmentId: 부서 ID
  - departmentName: 부서명
- pageable: 페이지 정보
  - pageNumber: 페이지 번호
  - pageSize: 페이지 크기
  - sort: 정렬 정보
  - employeeId: 직원 ID
  - offset: 오프셋
  - paged: 페이지 여부
  - unpaged: 페이지 여부
- totalPages: 전체 페이지 수
- totalElements: 전체 요소 수
- last: 마지막 페이지 여부
- size: 페이지 크기
- number: 페이지 번호
- sort: 정렬 정보
  - empty: 정렬 여부
  - sorted: 정렬 여부
  - unsorted: 정렬 여부
- numberOfElements: 요소 수
- first: 첫 페이지 여부
- empty: 비어있는지 여부

### 부서 정보 조회

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/department/100
```

- Method: GET
- URI: /department/{id}
- Path Variable:
  - id: 부서 ID

#### 응답

```json

{
  "id": 100,
  "departmentName": "Finance",
  "managerName": "Nancy Greenberg",
  "locationName": "Seattle",
  "employees": [
    {
      "id": 108,
      "firstName": "Nancy",
      "lastName": "Greenberg",
      "email": "NGREENBE",
      "phoneNumber": "515.124.4569",
      "hireDate": "1994-08-17",
      "jobId": "FI_MGR",
      "jobTitle": "Finance Manager",
      "salary": 15600.00,
      "commissionPct": null,
      "managerId": 101,
      "managerName": "Neena Kochhar",
      "departmentId": 100,
      "departmentName": "Finance"
    },
  ]
}

```

- id: 부서 ID
- departmentName: 부서명
- managerName: 관리자 이름
- locationName: 위치명
- employees: 직원 목록
  - id: 직원 ID
  - firstName: 이름
  - lastName: 성
  - email: 이메일
  - phoneNumber: 전화번호
  - hireDate: 입사일
  - jobId: 직무 ID
  - jobTitle: 직무명
  - salary: 급여
  - commissionPct: 커미션
  - managerId: 관리자 ID
  - managerName: 관리자 이름
  - departmentId: 부서 ID
  - departmentName: 부서명

### 위치 정보 조회

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/location/1000
```

- Method: GET
- URI: /location/{id}
- Path Variable:
  - id: 위치 ID


#### 응답

```json

{
    "id": 1000,
    "streetAddress": "1297 Via Cola di Rie",
    "postalCode": "00989",
    "city": "Roma",
    "stateProvince": null,
    "countryId": "IT",
    "countryName": "Italy"
}

```

- id: 위치 ID
- streetAddress: 주소
- postalCode: 우편번호
- city: 도시
- stateProvince: 주
- countryId: 국가 ID
- countryName: 국가명

### 부서 급여 인상

#### 요청 

```shell
curl -X PUT http://52.10.69.37:8080/department/100/raise
``` 

- Method: PUT
- URI: /department/{id}/raise
- Path Variable:
  - id: 부서 ID
- Request Body:
  - raisePercentage: 인상율 (Integer)

#### 응답

```json
[
  {
    "id": 108,
    "firstName": "Nancy",
    "lastName": "Greenberg",
    "email": "NGREENBE",
    "phoneNumber": "515.124.4569",
    "hireDate": "1994-08-17",
    "jobId": "FI_MGR",
    "jobTitle": "Finance Manager",
    "salary": 15600.000,
    "commissionPct": null,
    "managerId": 101,
    "managerName": "Neena Kochhar",
    "departmentId": 100,
    "departmentName": "Finance"
  }
]
```

급여가 인상된 부서의 직원 목록을 반환합니다.

- id: 직원 ID
- firstName: 이름
- lastName: 성
- email: 이메일
- phoneNumber: 전화번호
- hireDate: 입사일
- jobId: 직무 ID
- jobTitle: 직무명
- salary: 급여
- commissionPct: 커미션
- managerId: 관리자 ID
- managerName: 관리자 이름
- departmentId: 부서 ID
- departmentName: 부서명

### 직원 정보 수정

#### 요청 

```shell
curl -X PUT http://52.10.69.37:8080/employee/101
```

- Method: PUT
- URI: /employee/{id}
- Path Variable:
  - id: 직원 ID
- Request Body:
  - firstName: 이름 (String)
  - lastName: 성 (String)
  - email: 이메일 (String)
  - phoneNumber: 전화번호 (String)
  - hireDate: 입사일 (String)
  - jobId: 직무 ID (String)
  - salary: 급여 (Double)
  - commissionPct: 커미션 (Double)
  - managerId: 관리자 ID (Integer)
  - departmentId: 부서 ID (Integer)

#### 응답

```json

{
    "id": 101,
    "firstName": "John",
    "lastName": "Doe",
    "email": "NKOCHHAR",
    "phoneNumber": "515.123.4568",
    "hireDate": "1989-09-21",
    "jobId": "AD_VP",
    "jobTitle": "Administration Vice President",
    "salary": 17000.00,
    "commissionPct": null,
    "managerId": 100,
    "managerName": "Steven King",
    "departmentId": 90,
    "departmentName": "Executive"
}

```

- id: 직원 ID
- firstName: 이름
- lastName: 성
- email: 이메일
- phoneNumber: 전화번호
- hireDate: 입사일
- jobId: 직무 ID
- jobTitle: 직무명
- salary: 급여
- commissionPct: 커미션
- managerId: 관리자 ID
- managerName: 관리자 이름
- departmentId: 부서 ID
- departmentName: 부서명

### 강좌 정보 검색

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/kmooc?page=1
```

- Method: GET
- URI: /kmooc
- Query Parameter:
  - page: 페이지 번호 (Integer)\

#### 응답

```json

{
    "content": [
        {
            "id": "course-v1:AICU+AICU01+2022_T2",
            "name": "학부모가 알아야 하는 인공지능 교육의 이해",
            "start": "2023-06-12T00:00:00",
            "description": "1. 인공지능 시대에 학부모가 자녀들을 위해 무엇을 가르쳐야 할 것인지(What)와 어떻게 가르쳐야 할 것인지(How)에 대한 것을 알고 이를 자녀 교육에 적용하여야 한다.\n2. 인공지능 시대가 요구하는 인재가 갖추어야 할 역량과 인공지능을 활용한 다양한 교육모델을 학습하고 자녀를 양육하는 학부모들에게도 자녀교육과 관련한 새로운 방향과 가치 인식을 통해 불확실한 미래에 무엇을 준비하고 어떻게 대비해야 하는지를 교육할 수 있어야 한다."
        },
        {
            "id": "course-v1:AICU+AICU02+2022_T2",
            "name": "체험하고 가르치는 인공지능 핵심개념",
            "start": "2023-06-12T00:30:00",
            "description": "1. 인공지능 교육의 정의와 필요성 및 교육 방법을 이해하고, 아이들에게 설명할 수 있다. \n2. 실제 아이들과 함께 엔트리를 이용하여 다양한 인공지능 활용 프로그램을 직접 만들어 보면서, 인공지능의 학습 원리를 이해하고, 인공지능 모델을 체험할 수 있다. "
        },
        {
            "id": "course-v1:AICU+AICU03+2022_T2",
            "name": "따라하며 이해 하는 인공지능 핵심원리",
            "start": "2023-06-12T00:00:00",
            "description": "1. 중학생 수준의 인공지능 교육 내용을 파악하고, 인공지능 교육을 위한 핵심 구성요소과 교육방법에 대해 이해한다. \n2. 중학교 아이들과 함게 다양한 인공지능 도구들을 활용하여 체험과 실습 중심으로 인공지능의 기본 원리를 이해한다. "
        },
        {
            "id": "course-v1:AICU+AICU04+2022_T2",
            "name": "함께 해결하는 인공지능과 응용",
            "start": "2023-06-12T00:00:00",
            "description": "1. 인공지능 용어에 대한 설명과 응용분야 및 활용분야들에 사례 중심 교육으로 인공지능에 문제 해결과 응용분야를 설명할 수 있다. \n2. 파이썬 언어와 라이브러리를 통하여 인공지능에 대한 실습을 해봄으로써 인공지능에 대한 이해와 인공지능에 대한 응용 능력을 습득할 수 있다."
        },
        {
            "id": "course-v1:AIIA+AIIA01+2022_T3_AIIA01",
            "name": "확률적 그래픽 모델",
            "start": "2022-08-05T00:00:00",
            "description": "본 강좌의 목적은 확률적 그래픽 모델의 개요를 이해하고, 이를 응용한 각종 딥러닝 기술들을 소개하는 것이다. 이 과정을 통해서 인공지능을 처음 접하는 수강생들도 인공지능 프로그래밍 기술들을 습득하는 것을 그 목표로 삼는다. 본 과정에서는 확률적 그래픽 모델을 기초로 하여 CNN, RNN, VAE, GAN, 강화학습 모델들의 응용 과정들을 경험하고 익히게 된다."
        },
        {
            "id": "course-v1:AIIA+AIIA02+2022_T3_AIIA02",
            "name": "전산인지과학 및 인간지능과 인공지능",
            "start": "2022-08-05T00:00:00",
            "description": "본 강좌는 전산 인지과학에 대한 범위와 요소 그리고 지능 정의와 측정에 대한 다양한 시각에 대해서 이해하고 인공지능의 주요 신경망과 시각지능, 언어지능, 데이터 분석 지능에 어떻게 활용되는지 알아본다. 인공지능을 동작시키기 위한 파이프라인 및 시스템에 대해서도 알아본다."
        },
        {
            "id": "course-v1:AJOU+AJOU01+2023_T1",
            "name": "인간의 판단과 의사결정",
            "start": "2023-03-01T15:00:00",
            "description": "인간의 판단과 의사결정 강의에 오신것을 환영합니다.\n인지심리학자 김경일 교수와 함께 인지심리에 대한 재미있는 강의가 진행 될 예정입니다."
        },
        {
            "id": "course-v1:AJOU+AJOU02+2023_T1",
            "name": "창업아이디어 탐색 및 실습",
            "start": "2023-03-01T15:00:00",
            "description": "창의적 사고, 팀웍과 의사소통, 문제해결 방법 등의 학습을 통해 시장성, 기술성, 사업성이 높은 아이디어를 도출하고, 이를 구체화함으로써 체계적인 아이디어 탐색 역량을 학습한다."
        },
        {
            "id": "course-v1:AJOU+AJOU03+2023_T1",
            "name": "설득을 위한 언어",
            "start": "2023-03-01T15:00:00",
            "description": "설득의 인지과학적 원리에 대한 정확하고 깊은 이해를 갖추고, 이를 실제 상황에 적용하여 상대를 설득할 수 있는 능력을 함께 함양하는 것을 목적으로 한다.\n설득의 정의, 사고의 프레임, 프레임의 도입 방식, 프레임의 작동 원리, 올바른 프레임의 사용법, 설득을 위해 필요한 관점 전환의 방법, 감정 및 욕구에 대한 이해, 인정 욕구의 방법 등을 원리 이해와 실례 분석을 통해 살펴본다."
        },
        {
            "id": "course-v1:ANU+ANU01+2020_T3",
            "name": "정보통신 보안",
            "start": "2020-10-12T00:00:00",
            "description": "본 강좌의 목적은 정보통신 보안 일반(암호화, 전자서명, 악성코드)와 네트워크, 서비스 및 시스템 보안을 배우고, 개인정보 보호 및 4차산업혁명의 보안이슈를 익히는 것이다. 수강자로 하여금 정보통신 보안의 기본 개념들을 이해하게 하여 보안을 분석하는 능력을 기르게 하고, 네트워크와 서비스를 이해하여 관련되는 문제점들을 파악하게 하여, 컴퓨터공학과 전공심화 학습에 도움을 주고자 한다."
        }
    ],
    "pageable": {
        "pageNumber": 2,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 20,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 254,
    "totalElements": 2537,
    "last": false,
    "size": 10,
    "number": 2,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 10,
    "first": false,
    "empty": false
}

```

- content: 강좌 정보 목록
  - id: 강좌 ID
  - name: 강좌명
  - start: 시작일
  - description: 강좌 설명
- pageable: 페이지 정보
    - pageNumber: 페이지 번호
    - pageSize: 페이지 크기
    - sort: 정렬 정보
    - employeeId: 직원 ID
    - offset: 오프셋
    - paged: 페이지 여부
    - unpaged: 페이지 여부
- totalPages: 전체 페이지 수
- totalElements: 전체 요소 수
- last: 마지막 페이지 여부
- size: 페이지 크기
- number: 페이지 번호
- sort: 정렬 정보
    - empty: 정렬 여부
    - sorted: 정렬 여부
    - unsorted: 정렬 여부
- numberOfElements: 요소 수
- first: 첫 페이지 여부
- empty: 비어있는지 여부

### 강좌 정보 단건 조회

#### 요청 

```shell
curl -X GET http://52.10.69.37:8080/kmooc/course-v1:AICU+AICU01+2022_T2
```

- Method: GET
- URI: /kmooc/{id}
- Path Variable:
  - id: 강좌 ID (String)

#### 응답

```json

{
  "id": "course-v1:AICU+AICU01+2022_T2",
  "name": "학부모가 알아야 하는 인공지능 교육의 이해",
  "start": "2023-06-12T00:00:00",
  "description": "1. 인공지능 시대에 학부모가 자녀들을 위해 무엇을 가르쳐야 할 것인지(What)와 어떻게 가르쳐야 할 것인지(How)에 대한 것을 알고 이를 자녀 교육에 적용하여야 한다.\n2. 인공지능 시대가 요구하는 인재가 갖추어야 할 역량과 인공지능을 활용한 다양한 교육모델을 학습하고 자녀를 양육하는 학부모들에게도 자녀교육과 관련한 새로운 방향과 가치 인식을 통해 불확실한 미래에 무엇을 준비하고 어떻게 대비해야 하는지를 교육할 수 있어야 한다."
}

```

- id: 강좌 ID
- name: 강좌명
- start: 시작일
- description: 강좌 설명
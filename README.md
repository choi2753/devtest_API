## 특정 고객 거래내역 조회 서비스 API

-----

### 1. API 기능
1. 연도별 합계금액이 가장 많은 고객 조회
2. 연도별 거래가 없는 고객 조회
3. 연도별 관리점별 거래금액 합계 조회
4. 관리점 이관
5. 관리점 삭제
6. 입력한 지점의 거래금액 합계 조회

-----

### 2. 개발환경
- Spring Boot Framework
- Java 1.8.0_161
- Gradle
- Dependencies
    - Spring Boot DevTools
    - Lombok
    - Spring Configuration Processor
    - Spring Web Starter
    - Spring Data JPA
    - H2 Database
    - JDBC API
    - MyBatis
- IntelliJ IDEA
- Postman

-----

### 3. 빌드 및 실행방법

1. https://github.com/choi2753/devtest_API에서 프로젝트를 다운받는다.
2. 다운받은 파일의 압축을 푼다
3. IntelliJ 실행 후 Import Project를 선택하고 압축을 풀었던 프로젝트 폴더에서
build.gradle을 선택하고 OK를 누른다.
4. Import Project from Gradle 창이 뜨면 상단에 Use auto-import만 체크하고 OK를 누른다.
5. 프로젝트를 불러오면서 Gradle이 라이브러리를 가져올 때 까지 기다린다.
6. 화면 우측에 Gradle 조작 창을 열고 `devtest/Tasks/application/bootrun`을
더블클릭해서 구동한다.
7. Unit Test는 Gradle 조작창의 `devtest/Tasks/verification/test`를
실행하면 수행할 수 있다.
8. API 테스트는 Postman 같은 API 테스트 도구로 수행해 볼 수 있다.

- API 기능명세 1번 테스트 방법

    1. 연도별 연간 합계 금액이 가장 많은 고객 조회 - GET
        - URL : http://localhost:8080/customer/annual/top
    
- API 기능명세 2번 테스트 방법

    1. 연도별 연간 거래가 없는 고객 조회 - GET
        - URL : http://localhost:8080/customer/annual/missing

- API 기능명세 3번 테스트 방법

    1. 연도별 관리점별 거래금액 조회 - GET
        - URL : http://localhost:8080/trnamt/branch/annual
    
- API 기능명세 4번 테스트 방법

    1. 관리점 이관 - PUT
        - URL : http://localhost:8080/transfer/branch
        - BODY : { "brCode":"~~현재관리점코드~~", "afterBrCode":"~~이관할관리점코드~~" }
        
    2. 관리점 삭제 - DELETE
        - URL : http://localhost:8080/transfer/branch
        - BODY : { "brCode":"~~삭제할관리점코드~~" }
        
    3. 관리점 거래금액 조회 - GET
        - URL : http://localhost:8080/trnamt/branch
        - BODY : { "brName":"~~관리점명~~" }

-----

### 4. 개발 중 문제해결

##### 4.1. 컨트롤러에서 @RestController, @RequestMapping 등 annotation이 안된다.

1. 라이브러리가 없다고 나온다.
2. IntelliJ에서 Gradle 기반의 프로젝트 작성시 가끔식 발생한다고 한다.
    
`Setting -> Build, ... -> Compiler ->Annotation Processors 에서 Enable annotation processing 체크`
    
- 필수적으로 해야하는 것은 맞지만 아직 해결되지 않았다.
    
`File -> Invalidate Caches / Restart... -> IntelliJ 재시작`
     
- 해결되지 않았다.
     
`dependencies에 implementation 'org.springframework.boot:spring-boot-starter-web 추가`
    
- 라이브러리가 없다는 메시지가 이것 때문이었던듯 하다. 해결되었다.
    
    
##### 4.2. 초기 데이터 적재

1. 서버 기동 시 csv로 첨부된 초기 데이터가 필요하다.

    - H2를 메모리DB로 설정하고 서버 기동시마다 테이블을 새로 생성하고
    데이터를 적재한다.

##### 4.3. 데이터 조회

1. JPA를 사용하고 있는데 CRUD는 어떻게 할 것인가

    - JPA에서 복잡한 SELECT 쿼리를 메소드 쿼리로 수행하기에는 가독성도
    떨어지고 없는 기능들도 많아 힘들다고 한다.

    - JPQL이라는걸 활용해도 되지만 역시 기존의 쿼리와 문법이 다르고
    JPA의 장점이 사라지는 방식이다.

    - CUD는 JPA가 편하다고 하니 JPA를 활용하고 SELECT 쿼리만 MyBatis를
    사용하기로 한다.

3. CUD를 수행해야는 단계가 되어 개발중인데 JPA로 CUD 개발도 잘 안되고 있다.

    - 아직 ORM 개념을 명확하게 이해하지 못한것 같다. 테이블 설계는
    잘 되어있는데 CUD를 못하고 있는 이유를 아직 찾지 못했다.

    - JPA로는 테이블 생성, 초기 데이터 적재만 수행하고 모든 CRUD는
    MyBatis로 수행하기로 했다. JPA에 대한 이해와 공부가 좀더 필요하다.

##### 4.4. 결과 리턴

1. 조회 결과를 HashMap에 받았더니 camel case 변환이랑 column 순서 유지가 안된다.


    public class DataMap extends LinkedHashMap {}

- LinkedHashMap을 상속받은 DataMap 클래스를 만든다. put 메소드를 오버라이드 해서
key 값을 camel case로 바꿔주는 메소드를 추가한다. HashMap 대신 DataMap을 사용한다.


##### 4.5. Project Export & Import

1. Application을 run 해서 구동했을 때는 한글 출력이 제대로 되는데 Gradle의
bootrun으로 구동하면 한글이 깨져서 나온다.

`파라미터 값, 출력 값 등 인코딩 UTF-8 변경`

- 효과가 없다.
- run과 bootrun 인코딩 문제는 검색해봐도 해결 할 방법이 없어서 못찾고 있다가
DB 데이터를 확인해 봤는데 DB에 들어가있는 데이터 인코딩이 잘못된 것을 발견..

`appliation.yml 설정파일에 spring.datasource.sql-script-encoding: UTF-8 추가`

- 해결되었다. sql script의 인코딩 설정이 안돼서 그랬다면 왜 application run 일 때는
문제가 없고 bootrun 때만 문제가 있던건지는 모르겠다.


---

### 5. 참고자료

- [victolee(https://victorydntmd.tistory.com)](https://victorydntmd.tistory.com/)
- [지단로보트(https://jsonobject.tistory.com/)](https://jsonobject.tistory.com/)
- [자바엔진(https://javaengine.tistory.com/)](https://javaengine.tistory.com/)
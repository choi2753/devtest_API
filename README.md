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

-----

### 4. 개발 중 문제해결

##### 1. 컨트롤러에서 @RestController, @RequestMapping 등 annotation이 안된다.

- 라이브러리가 없다고 나온다.
- IntelliJ에서 Gradle 기반의 프로젝트 작성시 가끔식 발생한다고 한다.
    
`Setting -> Build, ... -> Compiler ->Annotation Processors 에서 Enable annotation processing 체크`
    
필수적으로 해야하는 것은 맞지만 아직 해결되지 않았다.
    
`File -> Invalidate Caches / Restart... -> IntelliJ 재시작`
     
해결되지 않았다.
     
`dependencies에 implementation 'org.springframework.boot:spring-boot-starter-web 추가`
    
라이브러리가 없다는 메시지가 이것 때문이었던듯 하다. 해결되었다.
    
    
##### 2. 초기 데이터 적재

- 서버 기동 시 csv로 첨부된 초기 데이터가 필요하다.

`H2를 메모리DB로 설정하고 서버 기동시마다 테이블을 새로 생성하고 데이터를 적재한다.`

##### 3. 데이터 조회

- JPA를 사용하고 있는데 CRUD는 어떻게 할 것인가

`JPA에서 복잡한 SELECT 쿼리를 메소드 쿼리로 수행하기에는 가독성도 떨어지고 없는 기능들도 많아 힘들다고 한다.`

`JPQL이라는걸 활용해도 되지만 역시 기존의 쿼리와 문법이 다르고 JPA의 장점이 사라지는 방식이다.`

`CUD는 JPA가 편하다고 하니 JPA를 활용하고 SELECT 쿼리만 MyBatis를 사용하기로 한다.`

- CUD를 수행해야는 단계가 되어 개발중인데 JPA로 CUD 개발도 잘 안되고 있다.

`아직 ORM 개념을 명확하게 이해하지 못한것 같다. 테이블 설계는 잘 되어있는데 CUD를 못하고 있는 이유를 아직 찾지 못했다.`

`JPA로는 테이블 생성, 초기 데이터 적재만 수행하고 모든 CRUD는 MyBatis로 수행하기로 했다. JPA에 대한 이해와 공부가 좀더 필요하다.`



---

* [Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


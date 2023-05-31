[뒤로](3-개발가이드.md)
## 3-1. Naming rules

1. 기본 명명규칙
- 모든 변수와 메소드명은 Camel-case 방식을 사용한다.
```java
private String someString;

public String returnSomeString() {
    return someString;
}
```
- 모든 클래스와 파일명은 Pascal-case 방식을 사용한다.
```java
Login.js
Main.js
CommonApiController.java

public class CommonApiController {
    ...
}
```
2. 메소드 명명규칙
- 목적-엔티티-상세 순서로 나열한다.
```java
public void saveQuestionByAdmin()

public void updateQuestionContents()

public void updateQuestionTitleByAdmin()

public ResponseEntity<> getQuestionList()
```

3. 스키마 명명규칙
- Entity이름은 겹치지않게 작성한다.
- 컬럼에 entity이름을 사용하지 않는다. (연관관계가 있는 엔티티의 경우 테이블명이 복잡해짐)
```java
@Entity
public class Article {
    private String articleTitle; ▷ X
    private String title; ▷ O
}
```

4. 클래스 명명규칙
- RestController ▷ {flag}{detail}ApiController.java
```java
QnaApiController
QnaRemoteApiController
MypageApiController
```
- Service ▷ {flag}{detail}Service.java
```java
QnaService
QnaRemoteService
UtilService
```
- JpaRepository ▷ {entity}JpaRepository.java
```java
QuestionJpaRepository
QuestionLikeJpaRepository
MemberJpaRepository
```
- RepositorySupport ▷ {entity}QuerydslRepository.java
```java
QuestionQuerydslRepository
```
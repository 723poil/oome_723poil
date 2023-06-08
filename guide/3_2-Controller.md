[뒤로](3-Spring개발가이드.md)
## 3-2. Controller

1. 의존성을 주입할 때는 반드시 final field로 선언하고 생성자 주입방식을 사용한다.
2. lombok @RequiredArgsConstructor를 활용한다
3. @RestController로 Rest컨트롤러임을 명시하고 @RequestMapping을 통해 비즈니스 로직의 기본 URL을 선언한다.
4. 모든 어노테이션은 중요한 순서대로 클래스와 가깝게 붙인다.
```java
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.qna-url}")
@RestController
public class QnaApiController {
    
}
```
5. 매핑을 할 때는 Restful 명명규칙을 따른다.
6. api method는 @{Method}Mapping을 사용한다.
7. 응답은 반드시 ResponseEntity 안에 담아서 처리한다.
8. Entity 객체를 직접 전달하지 않도록 주의한다. 반드시 DTO를 작성한다.
9. DB와의 통신은 Service에서 처리한다. 컨트롤러에서 데이터를 조작하는 로직을 작성하지 않는다.(단 형변환등의 작업은 가능하다)
```java
@GetMapping("/question/{questionId}")
public ResponseEntity<QuestionResDto> getQuestionDetail(
        @PathVariable("questionId") Long questionId) {
    return ResponseEntity.ok(qnaService.getQuestionDetail(questionId));
}
```
10. 데이터를 파라미터로 받을 때는 반드시 ReqDto를 활용한다.
[뒤로](3-Spring개발가이드.md)
## 3-5. Dto

1. Dto는 Res와 Req를 구분한다.
2. req dto는 다음 어노테이션을 필수 부착한다.
```java
@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
```
3. res dto는다음 어노테이션을 필수 부착한다.
```java
@ToString
@Data
@Setter
@Getter
```
4. 모든 dto는 Serializable을 구현한다. 이 때 serialVersionUID를 필수 등록한다. 인텔리제이의 경우 다음 [문서](https://androowl.tistory.com/50)를 참고한다.
```java
@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class QnaSaveReqDto implements Serializable {

    private static final long serialVersionUID = 7570423749383770007L;
}
``` 
5. req dto의 경우 validation을 활용한다. [참고](https://www.baeldung.com/spring-boot-bean-validation)
6. 일반적으로 saveReqDto의 경우 toEntity() 메소드를 구현한다. (필수사항아님)
```java
public Qna toEntity() {
    if (qnaType.equals(QnaType.A) && ObjectUtils.isEmpty(parentQna)) {
        throw new IllegalArgumentException("부모 QNA가 비어있습니다.");
    }

    return Qna.builder()
            .title(title)
            .contents(contents)
            .creater(creater)
            .qnaType(qnaType)
            .parentQna(parentQna)
            .build();
}
```
7. res dto의 경우 entity를 생성자로 주입받아 완성한다. (필수사항아님, 단 Stream API 활용하기 편해짐)
```java
public QnaResDto(Qna entity) {
    this.title = entity.getTitle();
    this.contents = entity.getContents();
    // Fetch 방지
    if (entity.getQnaType().equals(QnaType.Q)) {
        this.answerList = entity.getAnswerList()
                .stream()
                .map(QnaResDto::new)
                .collect(Collectors.toList());
    }
    this.createrNickname = entity.getCreater().getNickname();
    this.createdDate = entity.getCreatedDate();
}
```
8. 날짜형식을 받아야 하는 경우
```java
// 날짜 + 시간일 경우 LocalDateTime으로 지정
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime someDateTime;

// 날짜만 있는경우 LocalDate로 지정
@JsonFormat(pattern = "yyyy-MM-dd")
private LocalDate someDate;

public MyEntity toEntity() {
    return MyEntity.builder()
        .someDateTime(someDate.asStartOfDay()) // LocalDate => LocalDateTime형식으로 변환
        .build();
}
```

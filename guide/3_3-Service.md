[뒤로](3-Spring개발가이드.md)
## 3-3. Service
1. @Service 어노테이션 선언한다.
2. 생성자 주입방식으로 DI한다.
3. JPA 영속성 컨택스트를 사용하기 때문에 jpaRepository에서 entity를 핸들링하는 모든 메소드에는 org.springframework.transaction.annotation.Transactional 어노테이션을 붙인다.
4. Entity를 리턴하지 않도록 주의한다.
5. 로그인된 유저를 핸들링 할 때는 다음 유틸을 사용한다. SecurityUtil.getCurrentMemberId()
6. 위 유틸은 헤더에 포함된 jwt토큰정보를 파싱해 어떤 유저의 요청인지 확인하고 유저 id를 리턴한다.
7. 필요한 경우 springframework.lang.NonNull 어노테이션을 적극 사용한다.
8. 완성된 메소드에는 반드시 javadoc을 작성한다.
9. redis 캐싱이 필요한 경우 다음과 같이 작성한다.
```java
// cacheManagerServer2를 cacheManager로 설정한다
// server1는 refresh token, 이메일 인증번호를 관리하는 서버
@Cacheable(
    value = "myQuestions",
    cacheManager = "cacheManagerServer2",
    keyGenerator = "customKeyGenerator"
)
@Transactional
public List<QnaResDto> getMyQuestionList(PageRequest pageable){
    Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(SecurityContextAuthenticationEmptyException::new);
    return qnaJpaRepository.findAllByCreaterAndQnaType(member, QnaType.Q, pageable).stream().map(QnaResDto::new).collect(Collectors.toList());
}
```
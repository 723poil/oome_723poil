[뒤로](3-Spring개발가이드.md)
## 3-5. Utils

1. MailSendService
   1. 이메일을 보내기 위한 서비스입니다. Bean으로 등록되어있기때문에 DI 하여 사용하시면 됩니다.
   2. 사용방법 예시
   ```java
   mailSendService.sendEmail(
                EmailSendDto.builder()
                        .to(email)
                        .subject("[OOME] 다음 인증번호를 입력하세요.")
                        .message(S.f(member.getNickname() + "님, 안녕하세요.\n" +
                                "OOME와 함께해주셔서 감사합니다.\n" +
                                "이메일 주소를 인증해주세요.\n" +
                                "인증번호 : {0}", String.valueOf(authCode) +
                                "감사합니다. \n" +
                                "OOME 드림.\n\n" +
                                "문의사항은 고객센터를 이용해주세요."))
                        .build()
        );
   ```
2. org.oome.infra.utils.SecurityUtils
   1. SecurityContext를 관리하는 유틸입니다.
   2. header JWT 토큰정보를 이용하여 로그인된 사용자를 식별합니다.
[뒤로](../README.md)
## 3. Naming Rules

### spring
- Controller ▷ {detail}{flag}Controller.java
- RestController ▷ {detail}{flag}ApiController.java
- Service ▷ {detail}{flag}Service.java
- Repository ▷ data  handler 정해진 후 작성예정
- Singleton Bean DI(dependency injection) 할 때 생성자 주입방식을 사용한다. (ex : lombok annotation)\
  올바르게 사용한 예
  ```java
  @RequiredArgsConstructor
  @RequestMapping
  @RestController
  public class MainCommonApiController {
    
    // 생성자 주입방식
    private final MainCommonService mainCommonService;
  
    //......
  }
  ```
  잘못 사용한 예
  ```java
  @RequestMapping
  @RestController
  public class MainCommonApiController {
    
    @Autowired
    private MainCommonService mainCommonService;
  
    //......
  }
  ```
- Bean의 역할을 의미하는 중요도가 높은 Annotation일수록 class명과 가깝게 위치한다.\
올바르게 사용된 예
  ```java
  @RequiredArgsConstructor
  @Service
  public class MainCommonService {
  }
  ```
  잘못 사용된 예
  ```java
  @Service
  @RequiredArgsConstructor
  public class MainCommonService {
  }
  ```
- Properties ▷ {detail}Properties.java
  - properties 파일은 @ConfigurationProperties로 정의한다.
  - prefix의 하위에 "is-use" 속성을 정의하고 @ConditionalOnProperty를 통해 사용중이지 않으면 Bean으로 생성되는 것을 방지한다.
  - Properties는 상단의 Annotation규칙보다 다음 규칙을 우선시한다.
  ```java
  @Bean
  @ConfigurationProperties(prefix = "org.oome.info.common")
  @ConditionalOnProperty(
          prefix = "org.oome.info.common", name = "is-use", havingValue = "true",
          matchIfMissing = true
  )
  public CommonInfoProperties commonInfoProperties() {
      return new CommonInfoProperties();    
  }
  ```
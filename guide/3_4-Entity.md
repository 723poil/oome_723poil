[뒤로](3-Spring개발가이드.md)
## 3-4. Entity

1. Entity의 이름은 고유해야한다.
2. Entity가 하위의 Entity를 가지게 되는 구조인 경우 폴더의 하위에 위치한다.
3. 다음 어노테이션을 필수로 사용한다.
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
```
4. 다음 id를 필수로 사용한다.
```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
```
5. 모든 필드는 컬럼이다. 별도 스키마정의가 필요하지 않더라도 @Column 어노테이션을 반드시 사용한다.
6. 만든이가 필요한 경우 다음과 같은 필드를 가진다.
```java
@ManyToOne
private Member creater;
```
7. Entity 필드가 있고 해당하는 Entity역시 하위에 Entity를 가진다면 불필요한 쿼리를 막기 위해 fetchType을 적절히 사용한다.
```java
@OneToMany(mappedBy = "parentQna", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Qna> answerList;
```
8. entity의 경로에 repository 패키지를 추가하고 JpaRepository를 생성한다.
```java
@Repository
public interface QnaJpaRepository extends JpaRepository<Qna, Long> {

}
```
9. 모든 entity는 BaseTimeEntity를 상속받는다.
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MyEntity extends BaseTimeEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
}
```
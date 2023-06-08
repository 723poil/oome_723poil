[뒤로](4-React개발가이드.md)
## 4-2. API 호출 가이드

1. CommonApi의 oomeApi를 import 합니다.
2. oomeApi의 fetchData(url, option)를 통해 api를 호출합니다.
3. 기본으로 method를 전달하지 않으면 GET방식으로 호출하게됩니다.
4. 마이크로서비스 단위로 getUrl을 통해 기본 url과 원하는 url을 합칠 수 있습니다
5. 예를 들어) oomeApi.QNA.getUrl('/list') => '/api/v1/qna/list'로 반환됩니다.
6. 하드코딩 방지를 위한 조치이니 필수로 적용하셔야합니다.
7. 기본 api호출방식은 axios로 이루어집니다. axios의 옵션을 커스텀으로 설정하실 수 있습니다.
8. 기본 header는 'Content-Type: application/json'입니다.
9. 추가적으로 var는 절대 지양합니다. const와 let을 사용합니다. [참고](https://gist.github.com/LeoHeo/7c2a2a6dbcf80becaaa1e61e90091e5d)

사용 예)
```javascript
import oomeApi from '../CommonApi'

const ReactFile = () => {
    
    const data = {
        'title': '테스트제목',
        'contents': '테스트 콘텐츠'
    }
    
    useEffect(() => {
        oomeApi.fetchData(oomeApi.QNA.getUrl('/question'), {
            method: 'POST',
            data: data
        }).then((data) => {
            console.log(`질문저장 완료, 저장된 질문번호 => ${data}`)
        })
    })
}
```
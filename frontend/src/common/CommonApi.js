import axios from 'axios';

/**
 * 기본 api 정보
 * @type {{EXT: {path: string, version: string}, UTILS: {path: string, version: string}, QNA: {path: string, version: string}, COMMON: {path: string, version: string}, BLOG: {path: string, version: string}}}
 */
const apiInfo = {
    COMMON: {
        version: 'v1',
        path: 'common'
    },
    QNA: {
        version: 'v1',
        path: 'qna'
    },
    BLOG: {
        version: 'v1',
        path: 'blog'
    },
    UTIL: {
        version: 'v1',
        path: 'util'
    },
    EXT: {
        version: 'v1',
        path: 'ext'
    }
}
const oomeApi = {}

/**
 * axios를 구현한 공통 axios
 * @param url
 * @param options
 * @returns {Promise<unknown>}
 */
oomeApi.fetchData = (url, options = {}) => {

    // if (isExpiredJwt()) {
    //     axios.defaults.headers.common['Authorization'] = null;
    //
    //     const isConfirm = window.confirm('로그인이 만료되었습니다.\n다시 로그인 하시겠습니까?');
    //
    //     if (isConfirm) {
    //         window.location.href = '/login';
    //         return Promise.reject(new Error('JWT_EXPIRED'));
    //     }
    // }
    // 기본 옵션 설정
    const defaultOptions = {
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        },
        // TODO: 기타 기본 옵션
    };

    // 사용자 정의 옵션과 기본 옵션을 병합
    const mergedOptions = {
        ...defaultOptions,
        ...options,
    };

    return axios(url, mergedOptions)
        .then(response => {
            // 응답 처리
            return response.data;
        })
        .catch(error => {
            if (error.request && error.request.response === 'UNAUTHORIZED') {
                alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.');
                window.location.href = '/login';
            }
            // 오류 처리
            else if (error.response && error.response.status === 406) {
                // 토큰이 만료되었으므로 서버에 재발급 요청
                return refreshToken()
                    .then(() => {
                        // 토큰 재발급 후에 원래 요청 다시 시도
                        return axios(url, mergedOptions);
                    })
                    .then(response => {
                        // 응답 처리
                        return response.data;
                    })
                    .catch(error => {
                        // 재시도 후에도 오류가 발생하면 처리
                        alert('처리중 오류가 발생했습니다.\n' + JSON.stringify(error));
                        console.log(JSON.stringify(error, null, 2));
                        throw error;
                    });
            } else {
                // 다른 오류 처리
                alert('처리중 오류가 발생했습니다.\n' + JSON.stringify(error));
                console.log(JSON.stringify(error, null, 2));
                throw error;
            }
        });
}

// 토큰 재발급 요청 함수
const refreshToken = () => {
    // TODO: 토큰 재발급 요청 로직 구현
    //  이 함수에서는 서버에 토큰 재발급을 요청하고, 새로운 토큰을 받아와서 적절한 처리를 해야 함.
    //  localStorage에 새로운 토큰을 저장하거나, 인증 헤더를 업데이트하는 등의 작업이 필요할 수 있음.
    //  이 예제에서는 refreshToken 함수를 통해 토큰 재발급 요청을 한다고 가정하고, 그 결과로 Promise를 리턴.
    return new Promise((resolve, reject) => {
        // TODO: 토큰 재발급 요청 로직 구현
        //  axios를 사용하여 서버에 토큰 재발급을 요청하고, Promise를 사용하여 결과를 처리할 수 있다.
        //  axios.post('/refresh-token').then(response => { ... }) 등으로 구현 가능.
        //  토큰 재발급에 성공하면 resolve()를 호출하고, 실패하면 reject()를 호출.
    });
}

const isExpiredJwt = () => {

    const storedExpirationDate = localStorage.getItem('expirationTime') || '0';
    console.log(storedExpirationDate)
    console.log(localStorage.getItem('isLoggedIn'))
    const currentTime = new Date().getTime();
    const adjExpirationTime = new Date(+ storedExpirationDate).getTime();
    const remainingDuration = adjExpirationTime - currentTime;

    if(storedExpirationDate !== '0' && remainingDuration <= 1000) {
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('expirationTime');
        return true
    }

    return false;
}

oomeApi.COMMON = {
    getUrl: (url) => {
        return `/api/${apiInfo.COMMON.version}/${apiInfo.COMMON.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.COMMON.getUrl()}/admin${url}`;
    }
}
oomeApi.QNA = {
    getUrl: (url) => {
        return `/api/${apiInfo.QNA.version}/${apiInfo.QNA.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.QNA.getUrl()}/admin${url}`;
    }
}
oomeApi.UTIL = {
    getUrl: (url) => {
        return `/api/${apiInfo.UTIL.version}/${apiInfo.UTIL.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.UTIL.getUrl()}/admin${url}`;
    }
}
oomeApi.BLOG = {
    getUrl: (url) => {
        return `/api/${apiInfo.BLOG.version}/${apiInfo.BLOG.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.BLOG.getUrl()}/admin${url}`;
    }
}
oomeApi.EXT = {
    getUrl: (url) => {
        return `/api/${apiInfo.EXT.version}/${apiInfo.EXT.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.EXT.getUrl()}/admin${url}`;
    }
}

export default oomeApi;
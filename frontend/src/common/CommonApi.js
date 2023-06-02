import axios from 'axios';
import oomeUtils from "./CommonUtils";

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
    UTILS: {
        version: 'v1',
        path: 'utils'
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
    // 기본 옵션 설정
    const defaultOptions = {
        method: 'GET',
        headers: {
            // TODO: 기본 헤더 설정
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
            // 오류 처리
            const errorMsg = new Error('처리중 오류가 발생했습니다.'); // 예시 오류 객체

            const shouldCopyError = window.confirm(`처리중 오류가 발생했습니다. 오류 내용을 복사하시겠습니까?\n오류 내용: ${JSON.stringify(error, null, 2)}`);

            if (shouldCopyError) {
                const copiedError = JSON.stringify(errorMsg, null, 2); // JSON 예쁘게 출력
                oomeUtils.copyToClipboard(copiedError);
                console.log('복사된 오류 내용:', copiedError);
            } else {
                console.log('오류 복사를 취소하였습니다.');
            }
            throw error;
        });
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
oomeApi.UTILS = {
    getUrl: (url) => {
        return `/api/${apiInfo.UTILS.version}/${apiInfo.UTILS.path}${url}`;
    },
    getAdminUrl: (url) => {
        return `${oomeApi.UTILS.getUrl()}/admin${url}`;
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
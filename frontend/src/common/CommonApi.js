import axios from 'axios';

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
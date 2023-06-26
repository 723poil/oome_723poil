package org.oome.core.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommonUrlProperties {

    private String commonUrl;

    private String qnaUrl;

    private String blogUrl;

    private String utilUrl;

    private String extUrl;

    private List<String> ignoredUrl;

    public String getCommonApi(String url) {
        return this.getCommonUrl() + url;
    }

    public String getQnaApi(String url) {
        return this.getQnaUrl() + url;
    }

    public String getUtilApi(String url) {
        return this.getUtilUrl() + url;
    }

    public String getBlogApi(String url) {
        return this.getBlogUrl() + url;
    }

    public String getExtApi(String url) {
        return this.getExtUrl() + url;
    }
}

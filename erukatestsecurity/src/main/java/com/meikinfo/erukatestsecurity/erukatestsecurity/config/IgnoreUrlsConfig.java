package com.meikinfo.erukatestsecurity.erukatestsecurity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略权限访问路径
 *
 * @author swh
 * @create: 2020-01-02 15:23
 */
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> url = new ArrayList<>();

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}

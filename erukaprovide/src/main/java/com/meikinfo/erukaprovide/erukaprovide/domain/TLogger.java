package com.meikinfo.erukaprovide.erukaprovide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TLogger {
    private Long id;

    private String groupId;

    private String unitId;

    private String tag;

    private String content;

    private String createTime;

    private String appName;
}
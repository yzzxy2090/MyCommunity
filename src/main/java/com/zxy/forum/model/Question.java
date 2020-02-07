package com.zxy.forum.model;

import lombok.Data;

//lombok.Data自动生成setter，getter，但无法指定参数
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}

package com.zxy.forum.dto;

import com.zxy.forum.model.User;

public class QuestionDTO {
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
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}

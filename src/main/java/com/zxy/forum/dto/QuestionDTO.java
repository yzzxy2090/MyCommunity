package com.zxy.forum.dto;

import com.zxy.forum.model.User;
import lombok.Data;

/**
 * model层是和数据库的数据类型相对应的
 * DTO（Data Transfer Object）：
 * 数据传输对象，这个概念来源于J2EE的设计模式，
 * 原来的目的是为了EJB的分布式应用提供粗粒度的数据实体，
 * 以减少分布式调用的次数，从而提高分布式调用的性能和降低网络负载，
 * 但在这里，我泛指用于展示层与服务层之间的数据传输对象。
 *
 * 比如这里model.Question不包含User属性
 * 但在使用时，creator是要和user关联起来的
 * 我们不可以改数据库模型，于是就用QuestionDTO来作为数据传输对象
 */

@Data
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
    //要将creator和user关联起来
    private User user;
}

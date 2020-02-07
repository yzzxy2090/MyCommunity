package com.zxy.forum.dto;

import lombok.Data;

//Github的access token
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}

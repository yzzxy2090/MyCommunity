package com.zxy.forum.controller;

import com.zxy.forum.dto.AccessTokenDTO;
import com.zxy.forum.dto.GithubUser;
import com.zxy.forum.mapper.UserMapper;
import com.zxy.forum.model.User;
import com.zxy.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //通过Github账户进行登录
        GithubUser githubUser = githubProvider.getUser(accessToken);

        //登陆成功
        if (githubUser != null) {
            //获取用户Github账户信息，生成token
            User user = new User();
            String token = UUID.randomUUID().toString();
            //将token放入user对象并存储到数据库中
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //将token放写入cookie中
            response.addCookie(new Cookie("token", token));
            //重定向至首页
            return "redirect:/";
        } else {
            //登陆失败，，请重新登陆
            return "redirect:/";
        }
    }
}

package com.zxy.forum.controller;

import com.zxy.forum.mapper.UserMapper;
import com.zxy.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    //通过UserMapper访问数据库中的User
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        //通过HttpServletRequest获得向服务器端发送请求的cookie
        //(HttpServletResponse是向服务器发送请求后返回浏览器的cookie，可用来设置这个cookie)
        Cookie[] cookies = request.getCookies();

        /*
        访问首页时循环遍历所有cookie
        拿到名称为"token"的cookie去数据库进行查询是否有该条cookie的记录
        若有就把数据库中该条记录对应的User对象放入session中
        使得前端可以据此判断是展示"我"还是"登录"
        */
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                //向数据库传一个token参数以获取相对应的User
                User user = userMapper.findByToken(token);
                //若从数据库查到的User对象不为空，则说明登陆成功
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}

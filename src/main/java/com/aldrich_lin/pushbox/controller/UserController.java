package com.aldrich_lin.pushbox.controller;

import com.aldrich_lin.pushbox.entity.User;
import com.aldrich_lin.pushbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //注解：告诉Spring Boot，当前方法用于处理请求“/users”
    @RequestMapping("/users")
    //注解：告诉Spring Boot，当前方法返回的对象转换为JSON
    @ResponseBody
    public List<User> getAll(){

        List<User> users = (List<User>) userRepository.findAll(Sort.by("id"));
        System.out.println("共有"+userRepository.count()+"条数据");
        return users;
    }



    @RequestMapping("/users/create")
    @ResponseBody
    public String create(String username, String password) {
        System.out.println("注册成功: 用户名"+username+"  密码"+password);
        userRepository.save(new User(username, password));

        return "注册成功";

    }

    @RequestMapping("/index")
    public String index(){
        return "/index.html";
    }

    @RequestMapping("/game")
    public String game(){
        return "/game.html";
    }

//    @RequestMapping("/error")
//    public String error(){
//        return "/error.html";
//    }
}

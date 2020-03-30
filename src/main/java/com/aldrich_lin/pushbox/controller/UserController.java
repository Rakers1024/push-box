package com.aldrich_lin.pushbox.controller;

import com.aldrich_lin.pushbox.entity.User;
import com.aldrich_lin.pushbox.entity.UserLeve;
import com.aldrich_lin.pushbox.repository.UserRepository;
import com.aldrich_lin.pushbox.utlis.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    User nowUser;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("登录成功-"+currentUserName);
            for(User user : userRepository.findAll()){
                if(currentUserName.equals(user.getUsername())) {
                    nowUser = user;
                    break;
                }
            }
        }
        return "/game.html";
    }

    @RequestMapping("/rankpg")
    public String rank(){
        return "/rank.html";
    }

    @PostMapping("/leveUpload")
    @ResponseBody
    public String leveUpload(int leve){
        nowUser.setLeve(leve);
        nowUser.setDate(new Date(new java.util.Date().getTime()));
        userRepository.save(nowUser);
        return "200";
    }

    @GetMapping("/leveDownload")
    @ResponseBody
    public int leveDownload(){
        return nowUser.getLeve();
    }

    @RequestMapping("/rank")
    @ResponseBody
    public List<UserLeve> getRank(){
        List<User> users = (List<User>) userRepository.findAll(Sort.by("leve"));
        List<UserLeve> userLeves = new ArrayList<>();
        for(int i = users.size()-1; i >= 0; i--){
            userLeves.add(new UserLeve(users.get(i).getUsername(), users.get(i).getLeve(), users.get(i).getDate()));
        }
        return userLeves;
    }
}

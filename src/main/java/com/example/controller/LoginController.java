package com.example.controller;

import com.example.dao.UserMapper;
import com.example.entity.ResultMap;
import com.example.shiro.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        String realPassword = userMapper.getPassword(username);
        if (realPassword == null) {
            return new ResultMap().fail().code(401).message("用户名错误");
        } else if (!realPassword.equals(password)) {
            return new ResultMap().fail().code(401).message("密码错误");
        } else {
            return new ResultMap().success().code(200).message(JWTUtil.createToken(username));
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return new ResultMap().success().code(401).message(message);
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultMap notLogin() {
        return new ResultMap().success().message("您尚未登陆！");
    }
}

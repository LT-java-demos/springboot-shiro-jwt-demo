package com.example.controller;

import com.example.entity.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/welcome")
    public ResultMap login() {
        return new ResultMap().success().code(200).message("欢迎访问游客页面！");
    }
}

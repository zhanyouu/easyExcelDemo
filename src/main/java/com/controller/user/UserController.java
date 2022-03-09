package com.controller.user;


import com.alibaba.fastjson.JSONObject;
import com.domain.entity.user.User;

import com.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public User findById(@RequestBody JSONObject req) {
        log.info("我被请求了...");
        return this.userService.findById(req.getInteger("id"));
    }
}


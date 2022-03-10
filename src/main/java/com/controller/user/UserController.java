package com.controller.user;


import com.alibaba.fastjson.JSONObject;
import com.domain.entity.user.User;

import com.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


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
    @RequestMapping("/queryUsers")
    public List<User> findByIds(@RequestBody JSONObject req) {
        log.info("我被请求了...");
        return this.userService.findUsersByIds(Arrays.asList(new Integer[]{1,2,3}));
    }
}


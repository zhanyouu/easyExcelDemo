package com.controller.user;


import com.domain.entity.user.User;

import com.service.user.UserService;
import com.utils.ReadTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("我被请求了...");
        ReadTest readTest = new ReadTest();
        readTest.simpleRead();
        return this.userService.findById(id);
    }
}


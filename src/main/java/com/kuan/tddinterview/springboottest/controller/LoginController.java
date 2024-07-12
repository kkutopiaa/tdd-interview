package com.kuan.tddinterview.springboottest.controller;

import com.kuan.tddinterview.springboottest.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(path = "/api/v1/login")
@RestController
public class LoginController {

    @PostMapping
    public Map<String, Object> handleLogin() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Map.of("id", loggedInUser.getId());
    }


}

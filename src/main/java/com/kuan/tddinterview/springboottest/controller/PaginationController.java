package com.kuan.tddinterview.springboottest.controller;

import com.kuan.tddinterview.springboottest.UserVo;
import com.kuan.tddinterview.springboottest.serivce.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class PaginationController {

    UserService userService;

    public PaginationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/pagination")
    public Page<UserVo> getUsers( Pageable pageable) {

        return userService.getUsers(pageable).map(UserVo::fromUserModel);
    }
}

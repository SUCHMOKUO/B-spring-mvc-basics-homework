package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserDto userDto) {
        userService.register(userDto);
    }

    @GetMapping("/login")
    public UserDto login(@RequestParam
                         @NotEmpty(message = "用户名不为空")
                         @Length(min = 3, max = 10, message = "用户名不合法，长度必须在3到10之间")
                         @Pattern(regexp = "^\\w+$", message = "用户名不合法，只能由字母、数字或下划线组成")
                         String username,

                         @RequestParam
                         @NotEmpty(message = "密码不为空")
                         @Length(min = 5, max = 12, message = "密码不合法，长度必须在5到12之间")
                         String password) {
        return userService.login(username, password);
    }
}

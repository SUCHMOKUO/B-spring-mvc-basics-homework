package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message = "用户名不为空")
    @Length(min = 3, max = 10, message = "用户名不合法，长度必须在3到10之间")
    @Pattern(regexp = "^\\w+$", message = "用户名不合法，只能由字母、数字或下划线组成")
    private String username;

    @NotEmpty(message = "密码不为空")
    @Length(min = 5, max = 12, message = "密码不合法，长度必须在5到12之间")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;
}

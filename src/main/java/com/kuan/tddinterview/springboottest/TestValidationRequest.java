package com.kuan.tddinterview.springboottest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestValidationRequest {

    @Positive(message = "数量需要为正整数！")
    private Integer number;

    @Size(min = 2, max = 6, message = "内容长度需要为2-6之间！")
    private String content;


    @Email(message = "邮箱格式错误！")
    @NotNull(message = "邮箱不能为null！")
    private String email;


    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话格式错误！")
    private String phone;


}

package com.example.accountservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password; // 可选，如果不希望在响应中包含密码，可以在控制器中忽略此字段
    private String firstName;
    private String lastName;
    private String createdAt; // 字符串格式化时间
    private String updatedAt;
}

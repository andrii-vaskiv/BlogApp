package com.vaskiv.blogapp.dto;

import com.vaskiv.blogapp.model.User;

public class UserResponseDto {
    private Long id;
    private String name;
    private Integer age;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

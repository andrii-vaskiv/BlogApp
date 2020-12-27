package com.vaskiv.blogapp.dto;

import com.vaskiv.blogapp.model.User;

public class UserRequestDto {
    private String name;
    private Integer age;

    public UserRequestDto(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    public User toUser() {
        return new User(this.getName(), this.getAge());
    }
}

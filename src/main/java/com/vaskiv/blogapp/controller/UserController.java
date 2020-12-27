package com.vaskiv.blogapp.controller;

import com.vaskiv.blogapp.dto.UserRequestDto;
import com.vaskiv.blogapp.dto.UserResponseDto;
import com.vaskiv.blogapp.model.Color;
import com.vaskiv.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/users")
    public Iterable<UserResponseDto> getUsers(@RequestParam(required = false) Integer minAge,
                                              @RequestParam(required = false) Color withArticleColor) {
        if(minAge != null) {
            return userService.getUsersWithMinAge(minAge);
        }
        if(withArticleColor != null) {
            return userService.getUsersWithArticleColor(withArticleColor);
        }
        return userService.getAllUsers();
    }

    @GetMapping("/users/name")
    public Iterable<String> getUsers(@RequestParam(required = false) Integer minArticleCount) {
        if(minArticleCount != null) {
            return userService.getUserNamesWithMinArticleCount(minArticleCount);
        }
        return userService.getAllUserNames();
    }

    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserRequestDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/users/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

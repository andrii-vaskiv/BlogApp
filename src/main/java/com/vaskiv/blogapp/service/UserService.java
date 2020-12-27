package com.vaskiv.blogapp.service;

import com.vaskiv.blogapp.dto.UserRequestDto;
import com.vaskiv.blogapp.dto.UserResponseDto;
import com.vaskiv.blogapp.exception.UserNotFoundException;
import com.vaskiv.blogapp.model.Color;
import com.vaskiv.blogapp.model.User;
import com.vaskiv.blogapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Iterable<UserResponseDto> getAllUsers() {
        List<User> usersResult = repository.findAll();
        return usersResult.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public Iterable<UserResponseDto> getUsersWithMinAge(Integer minAge) {
        if(minAge == null) {
            throw new NullPointerException("minAge is null");
        }
        List<User> usersResult = repository.getByAgeGreaterThanEqual(minAge);
        return usersResult.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public Iterable<String> getUserNamesWithMinArticleCount(Integer minArticleCount) {
        if(minArticleCount == null) {
            throw new NullPointerException("minArticleCount is null");
        }
        return repository.getUserNamesWithMinArticleCount(minArticleCount);
    }

    public Iterable<UserResponseDto> getUsersWithArticleColor(Color color) {
        if(color == null) {
            throw new NullPointerException("color is null");
        }
        Integer i = color.ordinal();
        List<User> usersResult = repository.getUsersWithArticleColor(color.ordinal());
        return usersResult.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public Iterable<String> getAllUserNames() {
        return repository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public UserResponseDto createUser(UserRequestDto userDto) {
        User user = userDto.toUser();
        User savedUser = repository.save(user);
        return new UserResponseDto(savedUser);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userDto) {
        User toBeUpdatedUser = repository.getOne(id);
        if(toBeUpdatedUser == null) {
            throw new UserNotFoundException("user not found with id: " + id);
        }
        toBeUpdatedUser.setAge(userDto.getAge());
        toBeUpdatedUser.setName(userDto.getName());
        User updateUser = repository.save(toBeUpdatedUser);
        return new UserResponseDto(updateUser);
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

}

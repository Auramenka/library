package com.innowise.libraryapplicationsystem.service;

import com.innowise.libraryapplicationsystem.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
    UserDto updateUser(UserDto userDto);
    UserDto findById(Long id);

}

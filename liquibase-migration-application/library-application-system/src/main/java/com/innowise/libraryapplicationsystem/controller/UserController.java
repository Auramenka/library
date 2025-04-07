package com.innowise.libraryapplicationsystem.controller;

import com.innowise.libraryapplicationsystem.constants.ApiConstants;
import com.innowise.libraryapplicationsystem.dto.ApiResponse;
import com.innowise.libraryapplicationsystem.dto.UserDto;
import com.innowise.libraryapplicationsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.USERS_ENDPOINT)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ApiResponse.<UserDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .body(userService.saveUser(userDto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getAllUsers() {
        return ApiResponse.<List<UserDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .body(userService.getAllUsers())
                .build();
    }

    @GetMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<UserDto> getUserById(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        return ApiResponse.<UserDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(userService.findById(id))
                .build();
    }

    @DeleteMapping(ApiConstants.ID_ENDPOINT)
    public ApiResponse<String> deleteUser(@PathVariable(ApiConstants.ID_PATH_VARIABLE) Long id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .body(ApiConstants.DELETE_RESPONSE)
                .build();
    }

    @PutMapping
    public ApiResponse<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return ApiResponse.<UserDto>builder()
                .statusCode(HttpStatus.OK.value())
                .body(userService.updateUser(userDto))
                .build();
    }

}

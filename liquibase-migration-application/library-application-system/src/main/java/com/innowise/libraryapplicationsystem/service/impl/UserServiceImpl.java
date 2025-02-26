package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.UserDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.UserMapper;
import com.innowise.libraryapplicationsystem.model.User;
import com.innowise.libraryapplicationsystem.repository.UserRepository;
import com.innowise.libraryapplicationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "User doesn't exist";
    private static final String USER_IS_EMPTY = "UserDto is empty";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        checkUserDto(userDto);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User userFromDB = getUserFromDB(id);
        userRepository.deleteById(userFromDB.getId());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        checkUserDto(userDto);
        User userFromDB = getUserFromDB(userDto.getId());
        userFromDB.setName(userDto.getName());
        userFromDB.setEmail(userDto.getEmail());
        userFromDB.setPhone(userDto.getPhone());
        return userMapper.toDto(userRepository.save(userFromDB));
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(getUserFromDB(id));
    }

    private void checkUserDto(UserDto userDto) {
        if (userDto == null) {
            throw new NotExistsException(USER_IS_EMPTY);
        }
    }

    private User getUserFromDB(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotExistsException(USER_NOT_EXIST));
    }
}

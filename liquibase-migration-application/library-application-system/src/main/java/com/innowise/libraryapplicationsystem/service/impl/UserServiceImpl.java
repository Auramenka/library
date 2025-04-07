package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.UserDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
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

    private static final String USER_NOT_EXIST_ERR_MSG = "User doesn't exist";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
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
        if (!isUserExists(id)) {
            throw new EntityNotExistsException(USER_NOT_EXIST_ERR_MSG);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
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

    private boolean isUserExists(Long id) {
        return userRepository.existsById(id);
    }

    private User getUserFromDB(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotExistsException(USER_NOT_EXIST_ERR_MSG));
    }
}

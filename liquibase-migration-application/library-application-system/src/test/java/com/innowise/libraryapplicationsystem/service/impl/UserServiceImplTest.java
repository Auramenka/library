package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.UserDto;
import com.innowise.libraryapplicationsystem.exceptions.EntityNotExistsException;
import com.innowise.libraryapplicationsystem.mappers.UserMapper;
import com.innowise.libraryapplicationsystem.model.User;
import com.innowise.libraryapplicationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl testSubject;

    @Test
    void shouldSaveCurrentUser() {
        //given
        UserDto userDto = new UserDto();
        userDto.setName("User name");

        User user = new User();
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);
        //when
        UserDto result = testSubject.saveUser(userDto);
        //then
        assertNotNull(result);
        assertEquals("User name", result.getName());
        verify(userMapper, times(1)).toDto(user);
        verify(userMapper, times(1)).toEntity(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldGetAllCurrentUsers() {
        //given
        UserDto userDto = new UserDto();

        User user = new User();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDto(user)).thenReturn(userDto);
        //when
        List<UserDto> result = testSubject.getAllUsers();
        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userMapper, times(1)).toDto(user);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetCurrentUserById() {
        //given
        Long id = 100L;
        User user = new User();
        user.setId(id);

        UserDto userDto = new UserDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);
        //when
        UserDto result = testSubject.findById(id);
        //then
        assertNotNull(result);
        verify(userRepository, times(1)).findById(id);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void shouldNotGetCurrentUserById_thenException() {
        //given
        Long id = 100L;
        User user = new User();
        user.setId(id);
        //when
        EntityNotExistsException result = assertThrows(EntityNotExistsException.class, () -> testSubject.findById(id));
        //then
        assertEquals("User doesn't exist", result.getMessage());
    }

    @Test
    void shouldDeleteExistedUser() {
        //given
        Long id = 100L;
        User user = new User();
        user.setId(id);

        when(userRepository.existsById(id)).thenReturn(true);
        //when
        testSubject.deleteUser(id);
        //then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldNotDeleteUser_thenException() {
        //given
        Long id = 100L;
        User user = new User();
        user.setId(id);
        //when
        EntityNotExistsException result = assertThrows(EntityNotExistsException.class, () -> testSubject.deleteUser(id));
        //then
        assertEquals("User doesn't exist", result.getMessage());
    }

    @Test
    void shouldUpdateExistedUser() {
        //given
        UserDto userDto = new UserDto();

        User user = new User();
        when(userRepository.findById(userDto.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        //when
        UserDto result = testSubject.updateUser(userDto);
        //then
        assertNotNull(result);
        verify(userRepository, times(1)).findById(userDto.getId());
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void shouldNotUpdateUserDtoIsEmpty_thenException() {
        //given
        UserDto userDto = new UserDto();
        userDto.setId(null);
        //when
        EntityNotExistsException result = assertThrows(EntityNotExistsException.class, () -> testSubject.updateUser(userDto));
        //then
        assertEquals("User doesn't exist", result.getMessage());
    }
}
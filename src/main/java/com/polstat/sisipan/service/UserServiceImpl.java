/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.User;
import com.polstat.sisipan.mapper.UserMapper;
import com.polstat.sisipan.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmuammal
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }


    @Override
    public UserDto getUser(UserDto userDto) {
        // TODO Auto-generated method stub
        User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return (UserMapper.mapToUserDto(user));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // TODO Auto-generated method stub
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return (UserMapper.mapToUserDto(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public UserDto delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map((product) -> (UserMapper.mapToUserDto(product)))
                .collect(Collectors.toList());
        return userDtos;
    }

}

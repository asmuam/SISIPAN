/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.User;
import java.util.List;

/**
 *
 * @author asmuammal
 */
public interface UserService {
    public UserDto getUser(Long id);

    public List<UserDto> getUsers();

    public UserDto getUser(UserDto userDto);

    public UserDto createUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto);

    public UserDto delete(Long id);
}

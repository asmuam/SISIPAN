/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.rpc.ChangePasswordRequest;
import java.util.List;

/**
 *
 * @author asmuammal
 */
public interface UserService {

    public UserDto getUser(Long id);

    public List<UserDto> getUsers();

    public UserDto saveUser(UserDto userDto);

    public boolean delete(Long id);

    public UserDto createUser(UserDto user);

    public UserDto getUserByEmail(String email);

    public UserDto changePassword(UserDto userDto, ChangePasswordRequest request);
}

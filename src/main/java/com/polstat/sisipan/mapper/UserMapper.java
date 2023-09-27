/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.User;

/**
 *
 * @author asmuammal
 */
public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
    }

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
    }
}


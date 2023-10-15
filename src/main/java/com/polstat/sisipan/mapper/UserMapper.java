/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.User;
import com.polstat.sisipan.repository.MahasiswaRepository;

/**
 *
 * @author asmuammal
 */
@Component
public class UserMapper {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    public UserDto mapToUserDto(User user) {
        Long mahasiswaId = user.getMahasiswa() != null ? user.getMahasiswa().getId() : null;
        return UserDto.builder()
                .id(user.getId())
                .mahasiswa(mahasiswaId)
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .mahasiswa(toMahasiswa(userDto.getMahasiswa()))
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    Mahasiswa toMahasiswa(Long mahasiswa) {
        if (mahasiswa != null) {
            return mahasiswaRepository.getReferenceById(mahasiswa);
        }
        return null;
    }
}

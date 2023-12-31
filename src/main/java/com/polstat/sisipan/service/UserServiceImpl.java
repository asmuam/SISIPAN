/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.User;
import com.polstat.sisipan.mapper.UserMapper;
import com.polstat.sisipan.repository.UserRepository;
import com.polstat.sisipan.rpc.ChangePasswordRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author asmuammal
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUser(Long id) {
        try {
            User user = userRepository.getReferenceById(id);
            return (userMapper.mapToUserDto(user));
        } catch (Exception e) {
            throw new RuntimeException("Gagal mendapatkan pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            return userMapper.mapToUserDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Gagal mendapatkan pengguna berdasarkan email: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = userMapper.mapToUser(userDto);
            userRepository.save(user);
            return userMapper.mapToUserDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        try {
            User user = userRepository.save(userMapper.mapToUser(userDto));
            return (userMapper.mapToUserDto(user));
        } catch (Exception e) {
            throw new RuntimeException("Gagal menyimpan pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            userRepository.delete(userToDelete);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghapus pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDto> getUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDto> userDtos = users.stream()
                    .map((product) -> (userMapper.mapToUserDto(product)))
                    .collect(Collectors.toList());
            return userDtos;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil daftar pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto changePassword(UserDto userDto, ChangePasswordRequest request) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            // Kata sandi lama cocok, ganti dengan kata sandi baru
            String encodedPassword = passwordEncoder.encode(request.getNewPassword());
            user.setPassword(encodedPassword);

            // Simpan perubahan ke repository
            User updatedUser = userRepository.save(user);

            return userMapper.mapToUserDto(updatedUser);
        } else {
            throw new IllegalArgumentException("Kata sandi lama tidak cocok");
        }
    }
}

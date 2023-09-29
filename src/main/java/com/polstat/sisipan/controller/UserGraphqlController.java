/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author asmuammal
 */
@Controller
public class UserGraphqlController {

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<UserDto> users() {
        return userService.getUsers();
    }

    @QueryMapping
    public UserDto userById(@Argument Long id) {
        return userService.getUser(id);
    }

    @MutationMapping
    public UserDto register(@Argument String email, @Argument String password) {
        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .build();
        return userService.saveUser(userDto);
    }

    @MutationMapping
    public UserDto login(@Argument String email, @Argument String password) {
        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .build();
        UserDto user = userService.getUser(userDto);
        return (user);
    }

    @QueryMapping
    public UserDto profileById(@Argument Long id) {
        // Implementasikan logika untuk mendapatkan profil pengguna berdasarkan ID
        return userService.getUser(id);
    }

    @MutationMapping
    public UserDto updateUser(@Argument Long id, @Argument String email, @Argument String password) {
        // Implementasikan logika untuk memperbarui profil pengguna berdasarkan ID
        UserDto userDto = userService.getUser(id);
        // Update userDto sesuai dengan argumen yang diberikan
        userDto.setEmail(email);
        userDto.setPassword(password);
        return userService.saveUser(userDto);
    }

    @MutationMapping
    public UserDto changePassword(@Argument Long id, @Argument String oldPassword, @Argument String newPassword) {
        // Implementasikan logika untuk mengganti password pengguna berdasarkan ID
        UserDto userDto = userService.getUser(id);
        // Verifikasi apakah password lama sesuai dengan yang dimiliki pengguna
        if (!userDto.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Password lama tidak sesuai");
        }
        // Mengganti password dengan yang baru
        userDto.setPassword(newPassword);
        return userService.saveUser(userDto);
    }

    @MutationMapping
    public void deleteUser(@Argument Long id) {
        // Implementasikan logika untuk menghapus akun pengguna berdasarkan ID
        UserDto user = userService.getUser(id);
        userService.delete(user);
    }
}

// @PostMapping("/register")
// public ResponseEntity<?> register(@RequestBody UserRequest request) {
// try {
// UserDto userDto = UserDto.builder()
// .email(request.getEmail())
// .password(request.getPassword())
// .build();
// UserDto user = userService.createUser(userDto);
// return ResponseEntity.ok(user);
// } catch (Exception e) {
// String errString = e.getMessage();
// return ResponseEntity.badRequest().body(errString);
// }
// }
//
// @PostMapping("/login")
// public ResponseEntity<?> login(@RequestBody UserRequest request) {
// try {
// // implementasi untuk memeriksa keberadaan pengguna berdasarkan email dan
// password
// UserDto userDto = UserDto.builder()
// .email(request.getEmail())
// .password(request.getPassword())
// .build();
// UserDto user = userService.getUser(userDto);
// if (user != null) {
// return ResponseEntity.ok("Login berhasil" + user);
// } else {
// return ResponseEntity.badRequest().body("Login gagal");
// }
// } catch (Exception e) {
// String errString = e.getMessage();
// return ResponseEntity.badRequest().body(errString);
// }
// }
//
// @GetMapping("/profile/{id}")
// public ResponseEntity<?> userById(@PathVariable Long id) {
// // Implementasi untuk mendapatkan informasi pengguna berdasarkan ID
// UserDto user = userService.getUser(id);
// if (user != null) {
// return ResponseEntity.ok(user);
// } else {
// return ResponseEntity.notFound().build();
// }
// }
//
// @PatchMapping("/profile/{id}")
// public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody
// UserRequest request) {
// try {
// // implementasi untuk mmerubah profile pengguna
// UserDto userDto = userService.getUser(id);
// userDto.setEmail(request.getEmail());
// userDto.setPassword(request.getPassword());
// UserDto user = userService.updateUser(userDto);
// return ResponseEntity.ok(user);
// } catch (Exception e) {
// String errString = e.getMessage();
// return ResponseEntity.badRequest().body(errString);
// }
// }
//
// @PutMapping("/profile/{id}/change-password")
// public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody
// ChangePasswordRequest request) {
// try {
// // Implementasi untuk mengganti password pengguna berdasarkan ID
// UserDto userDto = userService.getUser(id);
// // Verifikasi apakah password lama sesuai dengan yang dimiliki pengguna
// if (!userDto.getPassword().equals(request.getOldPassword())) {
// return ResponseEntity.badRequest().body("Password lama tidak sesuai");
// }
// // Mengganti password dengan yang baru
// userDto.setPassword(request.getNewPassword());
// UserDto user = userService.updateUser(userDto);
// return ResponseEntity.ok(user);
// } catch (Exception e) {
// String errString = e.getMessage();
// return ResponseEntity.badRequest().body(errString);
// }
// }
//
// @DeleteMapping("/profile/{id}")
// public ResponseEntity<?> deleteUser(@PathVariable Long id) {
// try {
// // Implementasi untuk menghapus akun pengguna berdasarkan ID
// UserDto userDto = userService.getUser(id);
// userService.delete(id);
// return ResponseEntity.ok("Akun pengguna berhasil dihapus" + userDto);
// } catch (Exception e) {
// String errString = e.getMessage();
// return ResponseEntity.badRequest().body(errString);
// }
// }

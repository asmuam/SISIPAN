/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.rpc.UserRequest;
import com.polstat.sisipan.service.UserService;
import com.polstat.sisipan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        try {
            User user = userService.createUser(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @PostMapping("/login")
    // membuat pilihan untuk id_mhs
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        return ResponseEntity.ok("your token");
    }
}

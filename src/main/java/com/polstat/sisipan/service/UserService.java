/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.entity.User;

/**
 *
 * @author asmuammal
 */
public interface UserService {
    public User getUser(Long id);
    public User createUser(String email, String password);
    public void updateUser(String email, String password) ;
    public void delete(Long id);
}

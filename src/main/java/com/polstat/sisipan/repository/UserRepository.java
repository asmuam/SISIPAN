/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.repository;

/**
 *
 * @author asmuammal
 */

import com.polstat.sisipan.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Hidden
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
}

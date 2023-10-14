/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.exception;

/**
 *
 * @author asmuammal
 */
public class JwtValidationException extends RuntimeException {
    public JwtValidationException(String message) {
        super(message);
    }
}


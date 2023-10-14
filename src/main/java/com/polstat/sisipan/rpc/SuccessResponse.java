/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.rpc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author asmuammal
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessResponse {
    private Object data;
    private String message;
    private String httpStatus;
    private int httpStatusCode;
    // Buatlah konstruktor, getter, dan setter sesuai kebutuhan.
}

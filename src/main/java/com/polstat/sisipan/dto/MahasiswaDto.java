/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.dto;

import com.polstat.sisipan.entity.Provinsi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author asmuammal
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaDto {

    private Long id;
    private String nim;
    private String name;
    private String prodi;
    private Provinsi provinsi;
    private Float ipk;
}

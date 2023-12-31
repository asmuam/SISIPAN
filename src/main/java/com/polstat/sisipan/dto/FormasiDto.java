/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.dto;

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
public class FormasiDto {

    private Long id;
    private Long provinsi;
    private String kodeSatker;
    private String namaSatuanKerja;
    private Integer kuotaSt;
    private Integer kuotaKs;
    private Integer kuotaD3;
}

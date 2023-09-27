/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String kodeProvinsi;
    private String kodeSatker;
    private String namaSatuankerja;
    private int kuotaSt;
    private int kuotaKs;
}

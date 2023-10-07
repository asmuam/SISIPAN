/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author asmua
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PilihanDto {

    private Long id;
    private Long mahasiswaId;
    private Long pilihan1;
    private Long pilihan2;
    private Long pilihan3;
    private Long pilihanSistem;
    private Float indeksPilihan1;
    private Float indeksPilihan2;
    private Float indeksPilihan3;
    private Float ipk;
    private Date waktuMemilih;
    private String hasil;

}

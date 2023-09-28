/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.entity.Pilihan;

/**
 *
 * @author asmuammal
 */
public class PilihanMapper {

    public static PilihanDto mapToPilihanDto(Pilihan pilihan) {
        return PilihanDto.builder()
                .build();
    }

    public static Pilihan mapToPilihan(PilihanDto pilihanDto) {
        return Pilihan.builder()
                .build();
    }
}

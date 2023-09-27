/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import com.polstat.sisipan.dto.FormasiDto;
import com.polstat.sisipan.entity.Formasi;

/**
 *
 * @author asmuammal
 */
public class FormasiMapper {

    public static FormasiDto mapToFormasiDto(Formasi formasi) {
        return FormasiDto.builder()
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
    }

    public static Formasi mapToFormasi(FormasiDto formasiDto) {
        return Formasi.builder()
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.entity.Mahasiswa;

/**
 *
 * @author asmuammal
 */
public class MahasiswaMapper {

    public static MahasiswaDto mapToMahasiswaDto(Mahasiswa member) {
        return MahasiswaDto.builder()
                .build();
    }

    public static Mahasiswa mapToMahasiswa(MahasiswaDto memberDto) {
        return Mahasiswa.builder()
                .build();
    }
}

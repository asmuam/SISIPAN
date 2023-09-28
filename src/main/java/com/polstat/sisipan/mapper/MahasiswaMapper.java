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

    public static MahasiswaDto mapToMahasiswaDto(Mahasiswa mahasiswa) {
        return MahasiswaDto.builder()
                .id(mahasiswa.getId())
                .nim(mahasiswa.getNim())
                .name(mahasiswa.getName())
                .prodi(mahasiswa.getProdi())
                .provinsi(mahasiswa.getProvinsi())
                .ipk(mahasiswa.getIpk())
                .build();
    }

    public static Mahasiswa mapToMahasiswa(MahasiswaDto mahasiswaDto) {
        return Mahasiswa.builder()
                .id(mahasiswaDto.getId())
                .nim(mahasiswaDto.getNim())
                .name(mahasiswaDto.getName())
                .prodi(mahasiswaDto.getProdi())
                .provinsi(mahasiswaDto.getProvinsi())
                .ipk(mahasiswaDto.getIpk())
                .build();
    }
}

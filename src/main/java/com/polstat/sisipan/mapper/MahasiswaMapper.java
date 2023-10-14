/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Provinsi;
import com.polstat.sisipan.repository.ProvinsiRepository;

/**
 *
 * @author asmuammal
 */

@Component
public class MahasiswaMapper {

    @Autowired
    ProvinsiRepository provinsiRepository;

    public MahasiswaDto mapToMahasiswaDto(Mahasiswa mahasiswa) {
        return MahasiswaDto.builder()
                .id(mahasiswa.getId())
                .nim(mahasiswa.getNim())
                .name(mahasiswa.getName())
                .prodi(mahasiswa.getProdi())
                .provinsi(mahasiswa.getProvinsi().getId())
                .ipk(mahasiswa.getIpk())
                .build();
    }

    public Mahasiswa mapToMahasiswa(MahasiswaDto mahasiswaDto) {
        return Mahasiswa.builder()
                .id(mahasiswaDto.getId())
                .nim(mahasiswaDto.getNim())
                .name(mahasiswaDto.getName())
                .prodi(mahasiswaDto.getProdi())
                .provinsi(toProvinsi(mahasiswaDto.getProvinsi()))
                .ipk(mahasiswaDto.getIpk())
                .build();
    }

    Provinsi toProvinsi(Long provinsi) {
        return provinsiRepository.getReferenceById(provinsi);
    }
}

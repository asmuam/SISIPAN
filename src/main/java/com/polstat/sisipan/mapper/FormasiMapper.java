/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polstat.sisipan.dto.FormasiDto;
import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Provinsi;
import com.polstat.sisipan.repository.ProvinsiRepository;

/**
 *
 * @author asmuammal
 */
@Component
public class FormasiMapper {

    @Autowired
    ProvinsiRepository provinsiRepository;

    public FormasiDto mapToFormasiDto(Formasi formasi) {
        FormasiDto formasiDto = FormasiDto.builder()
                .id(formasi.getId())
                .kodeSatker(formasi.getKodeSatker())
                .namaSatuanKerja(formasi.getNamaSatuanKerja())
                .kuotaKs(formasi.getKuotaKs())
                .kuotaSt(formasi.getKuotaSt())
                .kuotaD3(formasi.getKuotaD3())
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
        if (formasi.getProvinsi() != null) {
            formasiDto.setProvinsi(formasi.getProvinsi().getId());
        }
        return formasiDto;
    }

    public Formasi mapToFormasi(FormasiDto formasiDto) {
        Formasi formasi = Formasi.builder()
                .id(formasiDto.getId())
                .kodeSatker(formasiDto.getKodeSatker())
                .namaSatuanKerja(formasiDto.getNamaSatuanKerja())
                .kuotaKs(formasiDto.getKuotaKs())
                .kuotaSt(formasiDto.getKuotaSt())
                .kuotaD3(formasiDto.getKuotaD3())
                .kuotaD3Tersedia(formasiDto.getKuotaD3())
                .kuotaKsTersedia(formasiDto.getKuotaKs())
                .kuotaStTersedia(formasiDto.getKuotaSt())
                // Anda dapat menambahkan properti yang sesuai di sini
                .build();
        if (formasiDto.getProvinsi() != null) {
            formasi.setProvinsi(toProvinsi(formasiDto.getProvinsi()));
        }
        return formasi;

    }

    Provinsi toProvinsi(Long provinsi) {
        return provinsiRepository.getReferenceById(provinsi);
    }
}

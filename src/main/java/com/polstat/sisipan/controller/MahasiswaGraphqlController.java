/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.entity.Provinsi;
import com.polstat.sisipan.repository.ProvinsiRepository;
import com.polstat.sisipan.service.MahasiswaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author asmuammal
 */
@Controller
public class MahasiswaGraphqlController {

    @Autowired
    private MahasiswaService mahasiswaService;
    @Autowired
    private ProvinsiRepository provinsiRepository;

    @QueryMapping
    public List<MahasiswaDto> mahasiswa() {
        return mahasiswaService.getMahasiswa();
    }

    @QueryMapping
    public MahasiswaDto mahasiswaById(@Argument Long id) {
        return mahasiswaService.getMahasiswa(id);
    }

    @QueryMapping
    public List<MahasiswaDto> mahasiswaByIdProvinsi(@Argument Long idProvinsi) {
        return mahasiswaService.getMahasiswaByIdProvinsi(idProvinsi);
    }

    @MutationMapping
    public MahasiswaDto addMahasiswa(
            @Argument String nim,
            @Argument String name,
            @Argument String prodi,
            @Argument float ipk,
            @Argument Long provinsiId
    ) {
        Provinsi provinsi = provinsiRepository.getReferenceById(provinsiId);
        MahasiswaDto mahasiswaDto = MahasiswaDto.builder()
                .nim(nim)
                .name(name)
                .prodi(prodi)
                .ipk(ipk)
                .provinsi(provinsi)
                .build();
        return mahasiswaService.saveMahasiswa(mahasiswaDto);
    }

    @MutationMapping
    public MahasiswaDto updateMahasiswa(
            @Argument Long id,
            @Argument String nim,
            @Argument String name,
            @Argument String prodi,
            @Argument float ipk,
            @Argument Long provinsiId) {
        MahasiswaDto mahasiswaDto = mahasiswaService.getMahasiswa(id);
        Provinsi provinsi = provinsiRepository.getReferenceById(provinsiId);

        mahasiswaDto.setIpk(ipk);
        mahasiswaDto.setName(name);
        mahasiswaDto.setNim(nim);
        mahasiswaDto.setProdi(prodi);
        mahasiswaDto.setProvinsi(provinsi);
        mahasiswaDto.setName(name);

        return mahasiswaService.saveMahasiswa(mahasiswaDto);
    }

    @MutationMapping
    public void deleteMahasiswa(@Argument Long id) {
        // Implementasikan logika untuk menghapus akun pengguna berdasarkan ID
        MahasiswaDto mahasiswaDto = mahasiswaService.getMahasiswa(id);
        mahasiswaService.delete(mahasiswaDto);
    }
}

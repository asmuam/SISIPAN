/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.entity.Provinsi;
import com.polstat.sisipan.repository.ProvinsiRepository;
import com.polstat.sisipan.service.UserService;
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
public class ProvinsiGraphqlController {

    @Autowired
    private ProvinsiRepository provinsiRepository;

    @QueryMapping
    public List<Provinsi> provinsi() {
        return provinsiRepository.findAll();
    }

    @QueryMapping
    public Provinsi provinsiById(@Argument Long id) {
        return provinsiRepository.getReferenceById(id);
    }

    @MutationMapping
    public Provinsi addProvinsi(
            @Argument String kodeProvinsi,
            @Argument String namaProvinsi
    ) {
        Provinsi provinsi = Provinsi.builder()
                .kodeProvinsi(kodeProvinsi)
                .namaProvinsi(namaProvinsi)
                .build();
        return provinsiRepository.save(provinsi);
    }

    @MutationMapping
    public Provinsi updateProvinsi(
            @Argument Long idProvinsi,
            @Argument String kodeProvinsi,
            @Argument String namaProvinsi
    ) {
        Provinsi provinsi = provinsiRepository.getReferenceById(idProvinsi);

        provinsi.setKodeProvinsi(kodeProvinsi);
        provinsi.setNamaProvinsi(namaProvinsi);

        return provinsiRepository.save(provinsi);
    }

    @MutationMapping
    public void deleteProvinsi(@Argument Long idProvinsi) {
        // Implementasikan logika untuk menghapus akun pengguna berdasarkan ID
        Provinsi provinsi = provinsiRepository.getReferenceById(idProvinsi);
        provinsiRepository.delete(provinsi);
    }
}

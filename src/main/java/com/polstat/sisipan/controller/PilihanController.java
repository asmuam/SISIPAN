/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.PilihanRepository;
import com.polstat.sisipan.rpc.PilihanRequest;
import com.polstat.sisipan.service.PenempatanService;
import com.polstat.sisipan.service.PilihanService;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pilihan")
public class PilihanController {

    @Autowired
    private PilihanService pilihanService;

    @Autowired
    private PenempatanService penempatanService;

    // Endpoint untuk menambahkan pilihan
    @PostMapping("/{mhs_id}")
    public ResponseEntity<String> tambahPilihan(@PathVariable("mhs_id") Long mahasiswaId, @RequestBody PilihanRequest request) {
        Pilihan pilihan = pilihanService.createPilihan(mahasiswaId, request.getPilihan1(), request.getPilihan2(), request.getPilihan3());
        penempatanService.penempatan();
        return ResponseEntity.ok("Pilihan berhasil ditambahkan. ");
    }

}

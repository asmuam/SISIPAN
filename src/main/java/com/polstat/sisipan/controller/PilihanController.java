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
import java.util.Optional;

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
    private PenempatanService penempatanService;

    @Autowired
    private PilihanRepository pilihanRepository;

    @Autowired
    private FormasiRepository formasiRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private PilihanService pilihanService;

    // Endpoint untuk menambahkan pilihan
    @PostMapping("/{mhs_id}")
    public ResponseEntity<String> tambahPilihan(@PathVariable("mhs_id") Long mahasiswaId, @RequestBody PilihanRequest request) {
        try {

            // Temukan mahasiswa berdasarkan ID
            Mahasiswa mahasiswa = mahasiswaRepository.getReferenceById(mahasiswaId);
            if (mahasiswa == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mahasiswa dengan ID tersebut tidak ditemukan.");
            }

            // Membuat objek Pilihan
            Pilihan pilihan = Pilihan.builder()
                    .mahasiswa(mahasiswa)
                    .pilihan1(formasiRepository.getReferenceById(request.getPilihan1()))
                    .pilihan2(formasiRepository.getReferenceById(request.getPilihan2()))
                    .pilihan3(formasiRepository.getReferenceById(request.getPilihan3()))
                    .waktuMemilih(new Date())
                    .build();

            // Simpan pilihan
            float ip1 = pilihanService.getIndeksPilihan1(pilihan);
            float ip2 = pilihanService.getIndeksPilihan2(pilihan);
            float ip3 = pilihanService.getIndeksPilihan3(pilihan);
            pilihan.setIndeksPilihan1(ip1);
            pilihan.setIndeksPilihan2(ip2);
            pilihan.setIndeksPilihan3(ip3);
            String hasilPenempatan = penempatanService.getHasilPenempatan(pilihan);
            pilihan.setHasil(hasilPenempatan);
            // Simpan objek "Pilihan"
            pilihanRepository.save(pilihan);

            // Lakukan penempatan otomatis
            penempatanService.penempatanOtomatis();

            return ResponseEntity.ok("Pilihan berhasil ditambahkan.");
        } catch (Exception e) {
            // Tangani kesalahan
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Terjadi kesalahan dalam menambahkan pilihan: " + e.getMessage());
        }
    }

}

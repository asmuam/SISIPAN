/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.rpc.PilihanRequest;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.service.MahasiswaService;
import com.polstat.sisipan.service.PilihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/pilihan")
public class PilihanController {

    private final PilihanService pilihanService;
    private final MahasiswaService mahasiswaService;

    @Autowired
    public PilihanController(PilihanService pilihanService, MahasiswaService mahasiswaService) {
        this.pilihanService = pilihanService;
        this.mahasiswaService = mahasiswaService;
    }

    @GetMapping("/{mahasiswa_id}")
    // mengambil pilihan dari id_mhs
    public ResponseEntity<?> getPilihanById(@PathVariable Long mahasiswa_id) {
        try {
            // Cari pilihan berdasarkan ID mhs
            Pilihan pilihan = pilihanService.getPilihan(mahasiswa_id);

            if (pilihan != null) {
                // Membuat respons HATEOAS dengan data mahasiswa dan pilihan
                Mahasiswa mahasiswa = pilihan.getMahasiswa();

                // Membuat tautan HATEOAS ke detail peminjaman
                Link selfLink = WebMvcLinkBuilder.linkTo(PilihanController.class).slash("pilihan").slash(pilihan.getId()).withSelfRel();

                EntityModel<Pilihan> pilihanModel = EntityModel.of(pilihan);

                return ResponseEntity.ok(pilihanModel);
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Tangani eksepsi di sini
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @PostMapping("/{mahasiswa_id}")
    // membuat pilihan untuk id_mhs
    public ResponseEntity<?> createPilihan(@PathVariable Long mahasiswa_id, @RequestBody PilihanRequest request) {
        try {
            // Cari mahasiswa berdasarkan ID mhs
            MahasiswaDto mahasiswa = mahasiswaService.getMahasiswa(mahasiswa_id);

            if (mahasiswa != null) {
                // Buat pilihan
                Pilihan pilihan = pilihanService.createPilihan(request.getIdMahasiswa(), request.getPilihan1(), request.getPilihan2(), request.getPilihan3());

                // Membuat tautan HATEOAS ke detail peminjaman
                Link selfLink = WebMvcLinkBuilder.linkTo(PilihanController.class).slash("pilihan").slash(pilihan.getId()).withSelfRel();

                // Mengonversi objek Loan ke EntityModel dan menambahkan tautan HATEOAS
                EntityModel<Pilihan> pilihanModel = EntityModel.of(pilihan, selfLink);

                return ResponseEntity.ok(pilihanModel);
            }

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Tangani eksepsi di sini
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @PatchMapping("/{mahasiswa_id}")
    //edit pilihan dari id_mhs
    public ResponseEntity<?> updatePilihan(@PathVariable Long mahasiswa_id, @RequestBody PilihanRequest request) {
        try {
            // Cari pilihan berdasarkan ID mhs
            Pilihan pilihan = pilihanService.getPilihan(mahasiswa_id);

            if (pilihan != null) {
                pilihanService.updatePilihan(request.getIdMahasiswa(), request.getPilihan1(), request.getPilihan2(), request.getPilihan3());

                // Mengirimkan respons berhasil
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Tangani eksepsi di sini
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @DeleteMapping("/{mahasiswa_id}")
    //delete pilihan dari id_mhs
    public ResponseEntity<?> deletePilihan(@PathVariable Long mahasiswa_id) {
        try {
            // Cari pilihan berdasarkan ID
            Pilihan pilihan = pilihanService.getPilihan(mahasiswa_id);

            if (pilihan != null) {
                // Hapus pilihan dari database
                pilihanService.deleteByMahasiswaId(mahasiswa_id);

                // Mengirimkan respons berhasil
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Tangani eksepsi di sini
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }
}

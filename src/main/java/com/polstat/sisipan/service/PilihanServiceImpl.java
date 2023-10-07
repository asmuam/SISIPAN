/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.PilihanRepository;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author asmuammal
 */
@Service
public class PilihanServiceImpl implements PilihanService {

    @Autowired
    private PilihanRepository pilihanRepository;

    @Autowired
    private FormasiRepository formasiRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Override
    public Pilihan getPilihan(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @Transactional // Menandai metode ini sebagai transaksional
    public Pilihan createPilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3) {
        try {
            // Temukan mahasiswa berdasarkan ID
            Mahasiswa mahasiswa = mahasiswaRepository.getReferenceById(idMahasiswa);
            if (mahasiswa == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mahasiswa dengan ID tersebut tidak ditemukan.");
            }

            // Membuat objek Pilihan
            Pilihan pilihan = Pilihan.builder()
                    .mahasiswa(mahasiswa)
                    .pilihan1(formasiRepository.getReferenceById(pilihan1))
                    .pilihan2(formasiRepository.getReferenceById(pilihan2))
                    .pilihan3(formasiRepository.getReferenceById(pilihan3))
                    .ipk(mahasiswa.getIpk())
                    .waktuMemilih(new Date())
                    .build();

            // Hitung indeks pilihan dan hasil penempatan
            float ip1 = getIndeksPilihan1(pilihan);
            float ip2 = getIndeksPilihan2(pilihan);
            float ip3 = getIndeksPilihan3(pilihan);
            pilihan.setIndeksPilihan1(ip1);
            pilihan.setIndeksPilihan2(ip2);
            pilihan.setIndeksPilihan3(ip3);

            // Simpan objek "Pilihan"
            return pilihanRepository.save(pilihan);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Terjadi kesalahan dalam menambahkan pilihan: " + e.getMessage());
        }
    }

    @Override
    public void updatePilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByMahasiswaId(Long mahasiswa_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float getIndeksPilihan1(Pilihan pilihan) {
        int indeksDaerah;
        int indeksPrioritas = 3;
        float IPK = pilihan.getMahasiswa().getIpk();
        Long provinsiId1 = pilihan.getPilihan1().getProvinsi() != null ? pilihan.getPilihan1().getProvinsi().getId() : null;

        if (provinsiId1 != null && Objects.equals(provinsiId1, pilihan.getMahasiswa().getProvinsi().getId())) {
            indeksDaerah = 2;
        } else {
            indeksDaerah = 1;
        }

        float indeksGabungan = (float) (IPK * 0.7 + indeksDaerah * 0.2 + indeksPrioritas * 0.1);

        // Jika provinsiId1 null, maka hitung indeksGabungan khusus
        if (provinsiId1 == null) {
            indeksGabungan = (float) (IPK * 0.8 + indeksPrioritas * 0.2);
        }

        return indeksGabungan;
    }

    @Override
    public float getIndeksPilihan2(Pilihan pilihan) {
        int indeksDaerah;
        int indeksPrioritas = 2;
        float IPK = pilihan.getMahasiswa().getIpk();
        Long provinsiId2 = pilihan.getPilihan2().getProvinsi() != null ? pilihan.getPilihan2().getProvinsi().getId() : null;

        if (provinsiId2 != null && Objects.equals(provinsiId2, pilihan.getMahasiswa().getProvinsi().getId())) {
            indeksDaerah = 2;
        } else {
            indeksDaerah = 1;
        }

        float indeksGabungan = (float) (IPK * 0.7 + indeksDaerah * 0.2 + indeksPrioritas * 0.1);

        // Jika provinsiId2 null, maka hitung indeksGabungan khusus
        if (provinsiId2 == null) {
            indeksGabungan = (float) (IPK * 0.8 + indeksPrioritas * 0.2);
        }

        return indeksGabungan;
    }

    @Override
    public float getIndeksPilihan3(Pilihan pilihan) {
        int indeksDaerah;
        int indeksPrioritas = 1;
        float IPK = pilihan.getMahasiswa().getIpk();
        Long provinsiId3 = pilihan.getPilihan3().getProvinsi() != null ? pilihan.getPilihan3().getProvinsi().getId() : null;

        if (provinsiId3 != null && Objects.equals(provinsiId3, pilihan.getMahasiswa().getProvinsi().getId())) {
            indeksDaerah = 2;
        } else {
            indeksDaerah = 1;
        }

        float indeksGabungan = (float) (IPK * 0.7 + indeksDaerah * 0.2 + indeksPrioritas * 0.1);

        // Jika provinsiId3 null, maka hitung indeksGabungan khusus
        if (provinsiId3 == null) {
            indeksGabungan = (float) (IPK * 0.8 + indeksPrioritas * 0.2);
        }

        return indeksGabungan;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.entity.Pilihan;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmuammal
 */
@Service
public class PilihanServiceImpl implements PilihanService {

    @Override
    public Pilihan getPilihan(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pilihan createPilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

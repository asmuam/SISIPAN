/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.entity.Pilihan;

/**
 *
 * @author asmuammal
 */
public interface PilihanService {

    public Pilihan getPilihan(Long id);

    public Pilihan createPilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3);

    public void updatePilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3);

    public void delete(Long id);

    public void deleteByMahasiswaId(Long mahasiswa_id);

    public float getIndeksPilihan1(Pilihan pilihan);

    public float getIndeksPilihan2(Pilihan pilihan);

    public float getIndeksPilihan3(Pilihan pilihan);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.rpc.PilihanRequest;
import java.util.List;

/**
 *
 * @author asmuammal
 */
public interface PilihanService {


    public PilihanDto createPilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3);

    public float getIndeksByPilihan(PilihanDto pilihan, int indeksPrioritas);

    public List<PilihanDto> getMahasiswaByFormasi(Long formasiId);

    public List<PilihanDto> getAllPilihan();

    public PilihanDto updatePilihan(Long id, PilihanRequest request);

    public boolean deletePilihan(Long pilihanId);

    public void deleteAllPilihan();

}
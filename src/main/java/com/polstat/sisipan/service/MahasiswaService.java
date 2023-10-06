/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.MahasiswaDto;
import java.util.List;

/**
 *
 * @author asmuammal
 */

public interface MahasiswaService {

    public List<MahasiswaDto> getMahasiswa();

    public MahasiswaDto updateMahasiswa(MahasiswaDto memberDto);

    public MahasiswaDto getMahasiswa(Long id);

    public List<MahasiswaDto> getMahasiswaByIdProvinsi(Long idProvinsi);

    public MahasiswaDto saveMahasiswa(MahasiswaDto mahasiswaDto);

    public void delete(MahasiswaDto mahasiswaDto);
}

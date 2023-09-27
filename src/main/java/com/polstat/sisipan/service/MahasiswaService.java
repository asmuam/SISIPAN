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

    public MahasiswaDto createMahasiswa(MahasiswaDto memberDto);

    public List<MahasiswaDto> getMahasiswa();

    public MahasiswaDto updateMahasiswa(MahasiswaDto memberDto);

    public void deleteMahasiswa(MahasiswaDto memberDto);

    public MahasiswaDto getMahasiswa(Long id);
}

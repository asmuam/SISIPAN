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

    public Long getMahasiswaId(String nim);

    public MahasiswaDto updateMahasiswa(MahasiswaDto mahasiswaDto);

    public MahasiswaDto getMahasiswaById(Long id);

    public MahasiswaDto createMahasiswa(MahasiswaDto mahasiswaDto);

    public boolean deleteMahasiswa(Long id);
}

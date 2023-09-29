/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.mapper.MahasiswaMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polstat.sisipan.repository.MahasiswaRepository;

/**
 *
 * @author asmuammal
 */
@Service
public class MahasiswaServiceImpl implements MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Override
    public List<MahasiswaDto> getMahasiswa() {
        List<Mahasiswa> mahasiswa = (List<Mahasiswa>) mahasiswaRepository.findAll();
        List<MahasiswaDto> mahasiswaDtos = mahasiswa.stream()
                .map((product) -> (MahasiswaMapper.mapToMahasiswaDto(product)))
                .collect(Collectors.toList());
        return mahasiswaDtos;
    }

    @Override
    public MahasiswaDto updateMahasiswa(MahasiswaDto mahasiswaDto) {
        Mahasiswa existingMahasiswa = mahasiswaRepository.findById(mahasiswaDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Mahasiswa updatedMahasiswa = mahasiswaRepository.save(existingMahasiswa);
        return MahasiswaMapper.mapToMahasiswaDto(updatedMahasiswa);
    }

    @Override
    public MahasiswaDto getMahasiswa(Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.getReferenceById(id);
        return MahasiswaMapper.mapToMahasiswaDto(mahasiswa);
    }

    @Override
    public List<MahasiswaDto> getMahasiswaByIdProvinsi(Long id) {
        List<Mahasiswa> mahasiswa =  mahasiswaRepository.findByProvinsiId(id);
        List<MahasiswaDto> mahasiswaDtos = mahasiswa.stream()
                .map((product) -> (MahasiswaMapper.mapToMahasiswaDto(product)))
                .collect(Collectors.toList());
        return mahasiswaDtos;
    }

    @Override
    public MahasiswaDto saveMahasiswa(MahasiswaDto mahasiswaDto) {
        Mahasiswa mahasiswa = MahasiswaMapper.mapToMahasiswa(mahasiswaDto);
        Mahasiswa savedMember = mahasiswaRepository.save(mahasiswa);
        return MahasiswaMapper.mapToMahasiswaDto(savedMember);
    }

    @Override
    public void delete(MahasiswaDto mahasiswaDto) {
        Mahasiswa mahasiswaToDelete = mahasiswaRepository.findById(mahasiswaDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        mahasiswaRepository.delete(mahasiswaToDelete);
    }

}

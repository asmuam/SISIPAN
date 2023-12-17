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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.ProvinsiRepository;

/**
 *
 * @author asmuammal
 */
@Service
public class MahasiswaServiceImpl implements MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private ProvinsiRepository provinsiRepository;
    @Autowired
    private MahasiswaMapper mahasiswaMapper;

    @Override
    public Long getMahasiswaId(String nim) {
        try {
            System.out.println("idMHS="+mahasiswaRepository.findIdByNim(nim));
            return mahasiswaRepository.findIdByNim(nim);
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mencari ID mahasiswa berdasarkan NIM.", e);
        }
    }

    @Override
    public List<MahasiswaDto> getMahasiswa() {
        try {
            List<Mahasiswa> mahasiswa = (List<Mahasiswa>) mahasiswaRepository.findAll();
            List<MahasiswaDto> mahasiswaDtos = mahasiswa.stream()
                    .map((product) -> (mahasiswaMapper.mapToMahasiswaDto(product)))
                    .collect(Collectors.toList());
            return mahasiswaDtos;
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mengambil data mahasiswa.", e);
        }
    }

    @Override
    public MahasiswaDto updateMahasiswa(MahasiswaDto mahasiswaDto) {
        try {
            Mahasiswa existingMahasiswa = mahasiswaRepository.findById(mahasiswaDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mahasiswa dengan ID tersebut tidak ditemukan."));
            // Update properti mahasiswa yang diperlukan
            existingMahasiswa.setName(mahasiswaDto.getName());
            existingMahasiswa.setNim(mahasiswaDto.getNim());
            existingMahasiswa.setProdi(mahasiswaDto.getProdi());
            existingMahasiswa.setProvinsi(provinsiRepository.getReferenceById(mahasiswaDto.getProvinsi()));
            existingMahasiswa.setIpk(mahasiswaDto.getIpk());

            Mahasiswa updatedMahasiswa = mahasiswaRepository.save(existingMahasiswa);
            return mahasiswaMapper.mapToMahasiswaDto(updatedMahasiswa);
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mengupdate mahasiswa.", e);
        }
    }

    @Override
    public MahasiswaDto getMahasiswaById(Long id) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.getReferenceById(id);
            return mahasiswaMapper.mapToMahasiswaDto(mahasiswa);
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mengambil data mahasiswa berdasarkan ID.", e);
        }
    }

    @Override
    public MahasiswaDto createMahasiswa(MahasiswaDto mahasiswaDto) {
        try {
            Mahasiswa mahasiswa = mahasiswaMapper.mapToMahasiswa(mahasiswaDto);
            Mahasiswa savedMember = mahasiswaRepository.save(mahasiswa);
            return mahasiswaMapper.mapToMahasiswaDto(savedMember);
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam menambahkan mahasiswa.", e);
        }
    }

    @Override
    public boolean deleteMahasiswa(Long id) {
        try {
            Mahasiswa mahasiswaToDelete = mahasiswaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Mahasiswa dengan ID tersebut tidak ditemukan."));
            mahasiswaRepository.delete(mahasiswaToDelete);
            return true;
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam menghapus mahasiswa.", e);
        }
    }

}

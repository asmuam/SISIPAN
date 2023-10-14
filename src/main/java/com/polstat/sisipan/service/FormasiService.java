/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.FormasiDto;
import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.mapper.FormasiMapper;
import com.polstat.sisipan.repository.FormasiRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmuammal
 */
@Service
public class FormasiService {

    private final FormasiMapper formasiMapper;
    private final FormasiRepository formasiRepository;

    @Autowired
    public FormasiService(FormasiMapper formasiMapper, FormasiRepository formasiRepository) {
        this.formasiMapper = formasiMapper;
        this.formasiRepository = formasiRepository;
    }

    public List<FormasiDto> getAllFormasi() {
        try {
            List<Formasi> formasiList = formasiRepository.findAll();
            return formasiList.stream()
                    .map(formasiMapper::mapToFormasiDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            // Tangani pengecualian yang mungkin terjadi, contoh: Logging, atau lempar
            // pengecualian yang sesuai
            throw new RuntimeException("Terjadi kesalahan saat mengambil daftar formasi.", ex);
        }
    }

    public FormasiDto getFormasiById(Long id) {
        try {
            Optional<Formasi> formasi = formasiRepository.findById(id);
            return formasi.map(formasiMapper::mapToFormasiDto).orElse(null);
        } catch (Exception ex) {
            // Tangani pengecualian yang mungkin terjadi, contoh: Logging, atau lempar
            // pengecualian yang sesuai
            throw new RuntimeException("Terjadi kesalahan saat mencari formasi dengan ID: " + id, ex);
        }
    }

    public FormasiDto createFormasi(FormasiDto formasiDto) {
        try {
            Formasi formasi = formasiMapper.mapToFormasi(formasiDto);
            formasi.setKuotaD3Tersedia(formasiDto.getKuotaD3());
            formasi.setKuotaKsTersedia(formasiDto.getKuotaKs());
            formasi.setKuotaStTersedia(formasiDto.getKuotaSt());
            formasi = formasiRepository.save(formasi);
            return formasiMapper.mapToFormasiDto(formasi);
        } catch (Exception ex) {
            // Tangani pengecualian yang mungkin terjadi, contoh: Logging, atau lempar
            // pengecualian yang sesuai
            throw new RuntimeException("Terjadi kesalahan saat membuat formasi baru.", ex);
        }
    }

    public FormasiDto updateFormasi(FormasiDto formasiDto) {
        try {
            Formasi formasi = formasiMapper.mapToFormasi(formasiDto);
            formasi = formasiRepository.save(formasi);
            return formasiMapper.mapToFormasiDto(formasi);
        } catch (Exception ex) {
            // Tangani pengecualian yang mungkin terjadi, contoh: Logging, atau lempar
            // pengecualian yang sesuai
            throw new RuntimeException("Terjadi kesalahan saat memperbarui formasi.", ex);
        }
    }

    public boolean deleteFormasi(Long id) {
        try {
            if (formasiRepository.existsById(id)) {
                formasiRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            // Tangani pengecualian yang mungkin terjadi, contoh: Logging, atau lempar
            // pengecualian yang sesuai
            throw new RuntimeException("Terjadi kesalahan saat menghapus formasi dengan ID: " + id, ex);
        }
    }
}

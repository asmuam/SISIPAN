/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.mapper;

/**
 *
 * @author asmuammal
 */
import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PilihanMapper {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    @Autowired
    FormasiRepository formasiRepository;

    public PilihanDto mapToPilihanDto(Pilihan pilihan) {
        PilihanDto pilihanDto = new PilihanDto();
        pilihanDto.setId(pilihan.getId());
        pilihanDto.setMahasiswa(pilihan.getMahasiswa().getId());
        pilihanDto.setPilihan1(pilihan.getPilihan1() != null ? pilihan.getPilihan1().getId() : null);
        pilihanDto.setPilihan2(pilihan.getPilihan2() != null ? pilihan.getPilihan2().getId() : null);
        pilihanDto.setPilihan3(pilihan.getPilihan3() != null ? pilihan.getPilihan3().getId() : null);
        pilihanDto.setPilihanSistem(pilihan.getPilihanSistem() != null ? pilihan.getPilihanSistem().getId() : null);
        pilihanDto.setIndeksPilihan1(pilihan.getIndeksPilihan1());
        pilihanDto.setIndeksPilihan2(pilihan.getIndeksPilihan2());
        pilihanDto.setIndeksPilihan3(pilihan.getIndeksPilihan3());
        pilihanDto.setIpk(pilihan.getIpk());
        pilihanDto.setWaktuMemilih(pilihan.getWaktuMemilih());
        pilihanDto.setHasil(pilihan.getHasil());
        return pilihanDto;
    }

    public Pilihan mapToPilihan(PilihanDto pilihanDto) {
        Pilihan pilihan = new Pilihan();
        pilihan.setId(pilihanDto.getId());

        // Set mahasiswa berdasarkan mahasiswaId dari DTO
        Mahasiswa mahasiswa = getMahasiswaIfNotNull(pilihanDto.getMahasiswa());
        pilihan.setMahasiswa(mahasiswa);

        // Set formasi pilihan berdasarkan id dari DTO
        Formasi pilihan1 = getFormasiIfNotNull(pilihanDto.getPilihan1());
        Formasi pilihan2 = getFormasiIfNotNull(pilihanDto.getPilihan2());
        Formasi pilihan3 = getFormasiIfNotNull(pilihanDto.getPilihan3());
        Formasi pilihanSistem = getFormasiIfNotNull(pilihanDto.getPilihanSistem());

        pilihan.setPilihan1(pilihan1);
        pilihan.setPilihan2(pilihan2);
        pilihan.setPilihan3(pilihan3);
        pilihan.setPilihanSistem(pilihanSistem);

        pilihan.setIndeksPilihan1(pilihanDto.getIndeksPilihan1());
        pilihan.setIndeksPilihan2(pilihanDto.getIndeksPilihan2());
        pilihan.setIndeksPilihan3(pilihanDto.getIndeksPilihan3());
        pilihan.setIpk(pilihanDto.getIpk());
        pilihan.setWaktuMemilih(pilihanDto.getWaktuMemilih());
        pilihan.setHasil(pilihanDto.getHasil());
        // ...
        return pilihan;
    }

    private Mahasiswa getMahasiswaIfNotNull(Long id) {
        return (id != null) ? mahasiswaRepository.getReferenceById(id) : null;
    }

    private Formasi getFormasiIfNotNull(Long id) {
        return (id != null) ? formasiRepository.getReferenceById(id) : null;
    }

}

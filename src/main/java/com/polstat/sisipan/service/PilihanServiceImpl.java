/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.mapper.PilihanMapper;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.PilihanRepository;
import com.polstat.sisipan.rpc.PilihanRequest;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmuammal
 */
@Service
public class PilihanServiceImpl implements PilihanService {

    @Autowired
    private PilihanRepository pilihanRepository;

    @Autowired
    private FormasiRepository formasiRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private PilihanMapper pilihanMapper;

    @Override
    @Transactional // Menandai metode ini sebagai transaksional
    public PilihanDto createPilihan(Long idMahasiswa, Long pilihan1, Long pilihan2, Long pilihan3) {
        // Temukan mahasiswa berdasarkan ID
        Mahasiswa mahasiswa = mahasiswaRepository.getReferenceById(idMahasiswa);
        if (mahasiswa == null) {
            throw new IllegalArgumentException("Mahasiswa dengan ID tersebut tidak ditemukan.");
        }

        try {
            // Membuat objek Pilihan
            PilihanDto pilihan = PilihanDto.builder()
                    .mahasiswa(mahasiswa.getId())
                    .pilihan1(pilihan1)
                    .pilihan2(pilihan2)
                    .pilihan3(pilihan3)
                    .ipk(mahasiswa.getIpk())
                    .waktuMemilih(new Date())
                    .build();
            // Hitung indeks pilihan dan hasil penempatan
            float ip1 = getIndeksByPilihan(pilihan, 1);
            float ip2 = getIndeksByPilihan(pilihan, 2);
            float ip3 = getIndeksByPilihan(pilihan, 3);
            pilihan.setIndeksPilihan1(ip1);
            pilihan.setIndeksPilihan2(ip2);
            pilihan.setIndeksPilihan3(ip3);
            // Simpan objek "Pilihan"
            Pilihan pilihanSet = pilihanMapper.mapToPilihan(pilihan);
            Pilihan pilihanSaved = pilihanRepository.save(pilihanSet);
            return pilihanMapper.mapToPilihanDto(pilihanSaved);
        } catch (Exception e) {
            // Anda dapat menambahkan logging di sini
            throw new RuntimeException("Terjadi kesalahan dalam menambahkan pilihan.", e);
        }
    }

    @Override
    public float getIndeksByPilihan(PilihanDto pilihan, int indeksPrioritas) {
        try {
            int indeksDaerah;
            float IPK = pilihan.getIpk();
            Long provinsiIdPilihan1 = formasiRepository.getReferenceById(pilihan.getPilihan1()).getId();
            Long provinsiIdMahasiswa = mahasiswaRepository.getReferenceById(pilihan.getMahasiswa()).getProvinsi()
                    .getId();

            if (provinsiIdPilihan1 != null && Objects.equals(provinsiIdPilihan1, provinsiIdMahasiswa)) {
                indeksDaerah = 2;
            } else {
                indeksDaerah = 1;
            }

            float indeksGabungan = (float) (IPK * 0.7 + indeksDaerah * 0.2 + indeksPrioritas * 0.1);

            // Jika provinsiId1 null, maka hitung indeksGabungan khusus
            if (provinsiIdPilihan1 == null) {
                indeksGabungan = (float) (IPK * 0.8 + indeksPrioritas * 0.2);
            }

            return indeksGabungan;
        } catch (Exception e) {
            // Logging atau penanganan kesalahan lainnya dapat ditambahkan di sini
            throw new RuntimeException("Terjadi kesalahan dalam menghitung indeks pilihan.", e);
        }
    }

    @Override
    public List<PilihanDto> getMahasiswaByFormasi(Long formasiId) {
        try {
            List<Pilihan> pilihanList = pilihanRepository.findByFormasiId(formasiId);

            // Membuat daftar DTO untuk Pilihan
            List<PilihanDto> pilihanDtoList = new ArrayList<>();

            for (Pilihan pilihan : pilihanList) {
                PilihanDto pilihanDto = pilihanMapper.mapToPilihanDto(pilihan);
                pilihanDtoList.add(pilihanDto);
            }

            return pilihanDtoList;
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mengambil data mahasiswa berdasarkan formasi.", e);
        }
    }

    @Override
    public List<PilihanDto> getAllPilihan() {
        try {
            List<Pilihan> pilihanList = pilihanRepository.findAll();
            return pilihanList.stream()
                    .map(pilihanMapper::mapToPilihanDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam mengambil data semua pilihan.", e);
        }
    }

@Override
public PilihanDto updatePilihan(Long id, PilihanRequest request) {
    try {
        Pilihan existingPilihan = pilihanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pilihan dengan ID tersebut tidak ditemukan."));

        if (request.getPilihan1() != null) {
            existingPilihan.setPilihan1(formasiRepository.getReferenceById(request.getPilihan1()));
        }
        if (request.getPilihan2() != null) {
            existingPilihan.setPilihan2(formasiRepository.getReferenceById(request.getPilihan2()));
        }
        if (request.getPilihan3() != null) {
            existingPilihan.setPilihan3(formasiRepository.getReferenceById(request.getPilihan3()));
        }
        Pilihan updatedPilihan = pilihanRepository.save(existingPilihan);
        return pilihanMapper.mapToPilihanDto(updatedPilihan);
    } catch (Exception e) {
        // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan kesalahan yang sesuai
        throw new RuntimeException("Terjadi kesalahan dalam mengupdate pilihan.", e);
    }
}

    @Override
    public boolean deletePilihan(Long id) {
        try {
            Pilihan pilihanToDelete = pilihanRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pilihan dengan ID tersebut tidak ditemukan."));
            pilihanRepository.delete(pilihanToDelete);
            return true;
        } catch (Exception e) {
            // Penanganan kesalahan dapat ditambahkan di sini, seperti logging atau pesan
            // kesalahan yang sesuai
            throw new RuntimeException("Terjadi kesalahan dalam menghapus pilihan.", e);
        }
    }

    @Override
    public void deleteAllPilihan() {
        pilihanRepository.deleteAll();
    }

}

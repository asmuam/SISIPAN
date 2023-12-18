/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.PilihanRepository;
import com.polstat.sisipan.rpc.PenempatanResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmuammal
 */
@Service
public class PenempatanService {

    @Autowired
    private PilihanRepository pilihanRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private FormasiRepository formasiRepository;

    @Autowired
    private PilihanService pilihanService;

    public List<PenempatanResponse> getHasilPenempatan() {
        List<PenempatanResponse> hasilPenempatan = new ArrayList<>();

        try {
            List<PilihanDto> pilihanList = pilihanService.getAllPilihan();

            if (!pilihanList.isEmpty()) {
                for (PilihanDto pilihanDto : pilihanList) {
                    PenempatanResponse response = new PenempatanResponse();
                    response.setMahasiswa(mahasiswaRepository.getReferenceById(pilihanDto.getMahasiswa()));
                    if (pilihanDto.getHasil() == "Pilihan 1") {
                        response.setHasil(formasiRepository.getReferenceById(pilihanDto.getPilihan1())); // Sesuaikan dengan atribut yang sesuai di PilihanDto
                    } else if (pilihanDto.getHasil() == "Pilihan 2") {
                        response.setHasil(formasiRepository.getReferenceById(pilihanDto.getPilihan2())); // Sesuaikan dengan atribut yang sesuai di PilihanDto
                    } else if (pilihanDto.getHasil() == "Pilihan 3") {
                        response.setHasil(formasiRepository.getReferenceById(pilihanDto.getPilihan3())); // Sesuaikan dengan atribut yang sesuai di PilihanDto
                    } else {
                        response.setHasil(formasiRepository.getReferenceById(pilihanDto.getPilihanSistem())); // Sesuaikan dengan atribut yang sesuai di PilihanDto
                    }
                    hasilPenempatan.add(response);
                }
            }

        } catch (Exception ex) {
            // Handle exception
        }

        return hasilPenempatan;
    }

    public void penempatanAcak() {
        try {
            // List semua pilihan yang memiliki "Pilihan Sistem" di kolom hasil dan urutkan
            // berdasarkan ipk (tertinggi ke terendah)
            List<Pilihan> pilihanSistem = pilihanRepository.findByHasil("Pilihan Sistem");
            pilihanSistem.sort(Comparator.comparing(Pilihan::getIpk, Collections.reverseOrder())
                    .thenComparing(Pilihan::getWaktuMemilih));

            // Lakukan penempatan
            for (Pilihan pilihan : pilihanSistem) {
                // Siapkan dua daftar terpisah untuk kuota ST dan kuota KS
                List<Formasi> formasiStTersedia = formasiRepository.findByKuotaStTersediaGreaterThan(0);
                List<Formasi> formasiKsTersedia = formasiRepository.findByKuotaKsTersediaGreaterThan(0);
                List<Formasi> formasiD3Tersedia = formasiRepository.findByKuotaD3TersediaGreaterThan(0);

                // Filter formasi berdasarkan prodi mahasiswa
                List<Formasi> formasiProdi = new ArrayList<>();

                switch (pilihan.getMahasiswa().getProdi()) {
                    case D4_ST ->
                        formasiProdi.addAll(formasiStTersedia);
                    case D4_KS ->
                        formasiProdi.addAll(formasiKsTersedia);
                    case D3_ST ->
                        formasiProdi.addAll(formasiD3Tersedia);
                    default -> {
                    }
                }
                if (!formasiProdi.isEmpty()) {
                    // Pilih formasi pertama yang tersedia berdasarkan prodi
                    Formasi formasiPilihan = formasiProdi.get(0);

                    // Assign formasi tersebut ke mahasiswa di kolom "Pilihan Sistem" dan kurangi
                    // kuota tersedia
                    pilihan.setPilihanSistem(formasiPilihan);

                    // Kurangi kuota tersedia berdasarkan jenis formasi (ST atau KS)
                    if (formasiPilihan.getKuotaStTersedia() > 0) {
                        formasiPilihan.setKuotaStTersedia(formasiPilihan.getKuotaStTersedia() - 1);
                    } else if (formasiPilihan.getKuotaKsTersedia() > 0) {
                        formasiPilihan.setKuotaKsTersedia(formasiPilihan.getKuotaKsTersedia() - 1);
                    } else if (formasiPilihan.getKuotaD3Tersedia() > 0) {
                        formasiPilihan.setKuotaD3Tersedia(formasiPilihan.getKuotaD3Tersedia() - 1);
                    }

                    // Simpan perubahan
                    pilihanRepository.save(pilihan);
                    formasiRepository.save(formasiPilihan);
                } else {
                    // Tidak ada formasi yang tersedia untuk prodi mahasiswa
                    // Handle error atau log pesan kesalahan
                    // Misalnya: log.error("Tidak ada formasi tersedia untuk prodi mahasiswa: {}",
                    // pilihan.getMahasiswa().getProdi());
                }
            }
        } catch (Exception e) {
            // Handle exception
            // Misalnya: log.error("Terjadi kesalahan dalam penempatan otomatis: {}",
            // e.getMessage(), e);
        }
    }

    public void penempatan() {
        try {
            // Hitung jumlah pilihan yang ada di tabel Pilihan
            long jumlahPilihan = pilihanRepository.count();

            // Hitung jumlah mahasiswa yang ada di tabel Mahasiswa
            long jumlahMahasiswa = mahasiswaRepository.count();
            List<Pilihan> semuaPilihan = pilihanRepository.findAll();

            // Jika jumlah pilihan sama dengan jumlah mahasiswa
            if (jumlahPilihan == jumlahMahasiswa) {

                // Loop untuk pilihan 1
                for (Pilihan pilihan : semuaPilihan) {
                    if (pilihan.getHasil() == null) { // Pilihan belum memiliki hasil
                        Formasi pilihan1 = pilihan.getPilihan1();
                        Mahasiswa.Prodi prodiMahasiswa = pilihan.getMahasiswa().getProdi();
                        int kuota = getKuotaByProdi(pilihan1, prodiMahasiswa);

                        List<Pilihan> pilihanList = pilihanRepository.findAll().stream()
                                .filter(p -> p.getPilihan1().getId() == pilihan1.getId())
                                .filter(p -> p.getMahasiswa().getProdi() == (prodiMahasiswa))
                                .collect(Collectors.toList());

                        pilihanList.sort(Comparator.comparing(Pilihan::getIndeksPilihan1, Collections.reverseOrder())
                                .thenComparing(Pilihan::getWaktuMemilih));

                        if (pilihanList.indexOf(pilihan) < kuota) {
                            pilihan.setHasil("Pilihan 1");
                            if (null != prodiMahasiswa) // Mengurangkan kuota di formasi yang sesuai
                            {
                                switch (prodiMahasiswa) {
                                    case D4_ST:
                                        pilihan1.setKuotaStTersedia(pilihan1.getKuotaStTersedia() - 1);
                                        break;
                                    case D4_KS:
                                        pilihan1.setKuotaKsTersedia(pilihan1.getKuotaKsTersedia() - 1);
                                        break;
                                    case D3_ST:
                                        pilihan1.setKuotaD3Tersedia(pilihan1.getKuotaD3Tersedia() - 1);
                                        break;
                                    default:
                                        break;
                                }
                            }

                            pilihanRepository.save(pilihan);
                            formasiRepository.save(pilihan1); // Perbarui nilai kuota di formasi
                        }
                    }
                }

                // Loop untuk pilihan 2
                for (Pilihan pilihan : semuaPilihan) {
                    if (pilihan.getHasil() == null) { // Pilihan belum memiliki hasil
                        Formasi pilihan2 = pilihan.getPilihan2();
                        if (pilihan2 != null) {
                            Mahasiswa.Prodi prodiMahasiswa = pilihan.getMahasiswa().getProdi();
                            int kuota = getKuotaByProdi(pilihan2, prodiMahasiswa);

                            List<Pilihan> pilihanList = pilihanRepository.findAll().stream()
                                    .filter(p -> p.getPilihan2() != null)
                                    .filter(p -> p.getPilihan2().getId() == pilihan2.getId())
                                    .filter(p -> p.getMahasiswa().getProdi() == (prodiMahasiswa))
                                    .filter(p -> p.getHasil() == null) // Filter yang hasilnya masih null
                                    .collect(Collectors.toList());

                            pilihanList
                                    .sort(Comparator.comparing(Pilihan::getIndeksPilihan2, Collections.reverseOrder())
                                            .thenComparing(Pilihan::getWaktuMemilih));

                            if (pilihanList.indexOf(pilihan) < kuota) {
                                pilihan.setHasil("Pilihan 2");
                                // Mengurangkan kuota di formasi yang sesuai
                                if (prodiMahasiswa == Mahasiswa.Prodi.D4_ST) {
                                    pilihan2.setKuotaStTersedia(pilihan2.getKuotaStTersedia() - 1);
                                } else if (prodiMahasiswa == Mahasiswa.Prodi.D4_KS) {
                                    pilihan2.setKuotaKsTersedia(pilihan2.getKuotaKsTersedia() - 1);
                                } else if (prodiMahasiswa == Mahasiswa.Prodi.D3_ST) {
                                    pilihan2.setKuotaD3Tersedia(pilihan2.getKuotaD3Tersedia() - 1);
                                }

                                pilihanRepository.save(pilihan);
                                formasiRepository.save(pilihan2); // Perbarui nilai kuota di formasi
                            }
                        }
                    }
                }

                // Loop untuk pilihan 3
                for (Pilihan pilihan : semuaPilihan) {
                    if (pilihan.getHasil() == null) { // Pilihan belum memiliki hasil
                        Formasi pilihan3 = pilihan.getPilihan3();
                        if (pilihan3 != null) {
                            Mahasiswa.Prodi prodiMahasiswa = pilihan.getMahasiswa().getProdi();
                            int kuota = getKuotaByProdi(pilihan3, prodiMahasiswa);

                            List<Pilihan> pilihanList = pilihanRepository.findAll().stream()
                                    .filter(p -> p.getPilihan3() != null)
                                    .filter(p -> p.getPilihan3().getId() == pilihan3.getId())
                                    .filter(p -> p.getMahasiswa().getProdi() == (prodiMahasiswa))
                                    .filter(p -> p.getHasil() == null) // Filter yang hasilnya masih null
                                    .collect(Collectors.toList());

                            pilihanList
                                    .sort(Comparator.comparing(Pilihan::getIndeksPilihan3, Collections.reverseOrder())
                                            .thenComparing(Pilihan::getWaktuMemilih));

                            if (pilihanList.indexOf(pilihan) < kuota) {
                                pilihan.setHasil("Pilihan 3");
                                pilihanRepository.save(pilihan);
                                // Mengurangkan kuota di formasi yang sesuai
                                if (prodiMahasiswa == Mahasiswa.Prodi.D4_ST) {
                                    pilihan3.setKuotaStTersedia(pilihan3.getKuotaStTersedia() - 1);
                                } else if (prodiMahasiswa == Mahasiswa.Prodi.D4_KS) {
                                    pilihan3.setKuotaKsTersedia(pilihan3.getKuotaKsTersedia() - 1);
                                } else if (prodiMahasiswa == Mahasiswa.Prodi.D3_ST) {
                                    pilihan3.setKuotaD3Tersedia(pilihan3.getKuotaD3Tersedia() - 1);
                                }
                                formasiRepository.save(pilihan3); // Perbarui nilai kuota di formasi
                            } else {
                                pilihan.setHasil("Pilihan Sistem");
                                pilihanRepository.save(pilihan);
                            }
                        }
                    }
                }
                penempatanAcak();
            }
            // Iterate over the list and save each Pilihan PS:BIAR KUOTA TERSEDI UTK PENGHITUNGAN TERESET GPP BERAT BARU SKALA KECIL
            for (Pilihan pilihan : semuaPilihan) {
                pilihanRepository.save(pilihan);
            }

        } catch (Exception e) {
            // Tangani pengecualian
            e.printStackTrace(); // Tampilkan pengecualian pada konsol
            // Atau lakukan penanganan pengecualian sesuai kebutuhan Anda
        }
    }

    private int getKuotaByProdi(Formasi formasi, Mahasiswa.Prodi prodiMahasiswa) {
        int kuota = 0;

        switch (prodiMahasiswa) {
            case D4_ST:
                kuota = formasi.getKuotaStTersedia();
                break;
            case D4_KS:
                kuota = formasi.getKuotaKsTersedia();
                break;
            case D3_ST:
                kuota = formasi.getKuotaD3Tersedia();
                break;
        }

        return kuota;
    }

}

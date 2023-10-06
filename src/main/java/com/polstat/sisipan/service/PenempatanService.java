/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Pilihan;
import com.polstat.sisipan.repository.FormasiRepository;
import com.polstat.sisipan.repository.MahasiswaRepository;
import com.polstat.sisipan.repository.PilihanRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    
    public void penempatanOtomatis() {
        try {
            // Hitung jumlah pilihan yang ada di tabel Pilihan
            long jumlahPilihan = pilihanRepository.count();

            // Hitung jumlah mahasiswa yang ada di tabel Mahasiswa
            long jumlahMahasiswa = mahasiswaRepository.count();

            // Jika jumlah pilihan sama dengan jumlah mahasiswa
            if (jumlahPilihan == jumlahMahasiswa) {
                System.out.println("eksekusi penempatan otomatis");
                // List semua pilihan yang memiliki "Pilihan Sistem" di kolom hasil dan urutkan berdasarkan ipk (tertinggi ke terendah)
                List<Pilihan> pilihanSistem = pilihanRepository.findByHasil("Pilihan Sistem");
                pilihanSistem.sort(Comparator.comparing(Pilihan::getIpk, Collections.reverseOrder())
                        .thenComparing(Pilihan::getWaktuMemilih, Collections.reverseOrder()));

                // Lakukan penempatan
                for (Pilihan pilihan : pilihanSistem) {
                    // Siapkan dua daftar terpisah untuk kuota ST dan kuota KS
                    List<Formasi> formasiStTersedia = formasiRepository.findByKuotaStTersediaGreaterThan(0);
                    List<Formasi> formasiKsTersedia = formasiRepository.findByKuotaKsTersediaGreaterThan(0);
                    System.out.println(formasiKsTersedia);

                    // Filter formasi berdasarkan prodi mahasiswa
                    List<Formasi> formasiProdi = new ArrayList<>();

                    if (pilihan.getMahasiswa().getProdi().equals("ST")) {
                        formasiProdi.addAll(formasiStTersedia);
                    } else if (pilihan.getMahasiswa().getProdi().equals("KS")) {
                        formasiProdi.addAll(formasiKsTersedia);
                    }

                    if (!formasiProdi.isEmpty()) {
                        // Pilih formasi pertama yang tersedia berdasarkan prodi
                        Formasi formasiPilihan = formasiProdi.get(0);

                        // Assign formasi tersebut ke mahasiswa di kolom "Pilihan Sistem" dan kurangi kuota tersedia
                        pilihan.setPilihanSistem(formasiPilihan);

                        // Kurangi kuota tersedia berdasarkan jenis formasi (ST atau KS)
                        if (formasiPilihan.getKuotaStTersedia() > 0) {
                            formasiPilihan.setKuotaStTersedia(formasiPilihan.getKuotaStTersedia() - 1);
                        } else if (formasiPilihan.getKuotaKsTersedia() > 0) {
                            formasiPilihan.setKuotaKsTersedia(formasiPilihan.getKuotaKsTersedia() - 1);
                        }

                        // Simpan perubahan
                        pilihanRepository.save(pilihan);
                        formasiRepository.save(formasiPilihan);
                    } else {
                        // Tidak ada formasi yang tersedia untuk prodi mahasiswa
                        // Handle error atau log pesan kesalahan
                        // Misalnya: log.error("Tidak ada formasi tersedia untuk prodi mahasiswa: {}", pilihan.getMahasiswa().getProdi());
                    }
                }
            } else {
                System.out.println("belum semua mhs");
                // Jumlah pilihan tidak sama dengan jumlah mahasiswa
                // Handle error atau log pesan kesalahan
                // Misalnya: log.error("Jumlah pilihan ({}) tidak sama dengan jumlah mahasiswa ({})", jumlahPilihan, jumlahMahasiswa);
            }
        } catch (Exception e) {
            // Handle exception
            // Misalnya: log.error("Terjadi kesalahan dalam penempatan otomatis: {}", e.getMessage(), e);
        }
    }

    public String getHasilPenempatan(Pilihan pilihan) {
        // Ambil pilihan1 dari objek pilihan
        Formasi pilihan1 = pilihan.getPilihan1();
        // Ambil prodi mahasiswa dari objek pilihan
        String prodiMahasiswa = pilihan.getMahasiswa().getProdi();
        // Ambil daftar tabel pilihan dengan pilihan1 yang sama dengan pilihan1
        List<Pilihan> pilihanList = pilihanRepository.findByPilihan1(pilihan1);

        // Filter juga prodi mahasiswanya agar sesuai dengan prodi mahasiswa objek pilihan
        pilihanList = pilihanList.stream()
                .filter(p -> p.getMahasiswa().getProdi().equals(pilihan.getMahasiswa().getProdi()))
                .collect(Collectors.toList());

        // Urutkan tabel tersebut dari indekspilihan1 terbesar ke terkecil
        pilihanList.sort(Comparator.comparing(Pilihan::getIndeksPilihan1, Collections.reverseOrder())
                .thenComparing(Pilihan::getWaktuMemilih, Collections.reverseOrder()));

        // Cek urutan pilihan berada di nomor berapa
        int urutanPilihan = pilihanList.indexOf(pilihan);

        // Ambil kuota dari formasi yang terkait dengan pilihan1
        int kuota = 0;
        if (prodiMahasiswa.equals("ST")) {
            kuota = pilihan1.getKuotaSt();
        } else if (prodiMahasiswa.equals("KS")) {
            kuota = pilihan1.getKuotaKs();
        }

        if (urutanPilihan < kuota) {
            return "Pilihan 1";
        } else {
            // Lanjut ke pilihan2
            Formasi pilihan2 = pilihan.getPilihan2();

            if (pilihan2 != null) {
                // Ambil daftar tabel pilihan dengan pilihan2 yang sama dengan pilihan2
                pilihanList = pilihanRepository.findByPilihan2(pilihan2);

                // Filter juga prodi mahasiswanya agar sesuai dengan prodi mahasiswa objek pilihan
                pilihanList = pilihanList.stream()
                        .filter(p -> p.getMahasiswa().getProdi().equals(pilihan.getMahasiswa().getProdi()))
                        .collect(Collectors.toList());

                // Urutkan tabel tersebut dari indekspilihan1 terbesar ke terkecil
                pilihanList.sort(Comparator.comparing(Pilihan::getIndeksPilihan1, Collections.reverseOrder())
                        .thenComparing(Pilihan::getWaktuMemilih, Collections.reverseOrder()));

                urutanPilihan = pilihanList.indexOf(pilihan);

                // Ambil kuota dari formasi yang terkait dengan pilihan2
                if (prodiMahasiswa.equals("ST")) {
                    kuota = pilihan2.getKuotaSt();
                } else if (prodiMahasiswa.equals("KS")) {
                    kuota = pilihan2.getKuotaKs();
                }

                if (urutanPilihan < kuota) {
                    return "Pilihan 2";
                } else {
                    // Lanjut ke pilihan3
                    Formasi pilihan3 = pilihan.getPilihan3();

                    if (pilihan3 != null) {
                        // Ambil daftar tabel pilihan dengan pilihan3 yang sama dengan pilihan3
                        pilihanList = pilihanRepository.findByPilihan3(pilihan3);

                        // Filter juga prodi mahasiswanya agar sesuai dengan prodi mahasiswa objek pilihan
                        pilihanList = pilihanList.stream()
                                .filter(p -> p.getMahasiswa().getProdi().equals(pilihan.getMahasiswa().getProdi()))
                                .collect(Collectors.toList());

                        // Urutkan tabel tersebut dari indekspilihan1 terbesar ke terkecil
                        pilihanList.sort(Comparator.comparing(Pilihan::getIndeksPilihan1, Collections.reverseOrder())
                                .thenComparing(Pilihan::getWaktuMemilih, Collections.reverseOrder()));

                        urutanPilihan = pilihanList.indexOf(pilihan);
                        // Ambil kuota dari formasi yang terkait dengan pilihan1
                        if (prodiMahasiswa.equals("ST")) {
                            kuota = pilihan2.getKuotaSt();
                        } else if (prodiMahasiswa.equals("KS")) {
                            kuota = pilihan2.getKuotaKs();
                        }

                        if (urutanPilihan < kuota) {
                            return "Pilihan 3";
                        } else {
                            // Jika masih > dari kuota, maka "Pilihan Sistem"
                            return "Pilihan Sistem";
                        }
                    }
                }
            }
        }

        // Default jika tidak ada hasil yang sesuai
        return "Pilihan Sistem";
    }

}

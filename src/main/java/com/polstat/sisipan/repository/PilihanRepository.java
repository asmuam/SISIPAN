/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.repository;

/**
 *
 * @author asmuammal
 */
import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Pilihan;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface PilihanRepository extends JpaRepository<Pilihan, Long> {

    public List<Pilihan> findByHasil(String pilihan_Sistem);

    public List<Pilihan> findByPilihan3(Formasi pilihan3);

    public List<Pilihan> findByPilihan2(Formasi pilihan2);

    public List<Pilihan> findByPilihan1(Formasi pilihan1);

    @Query("SELECT p FROM Pilihan p WHERE p.pilihan1 = :formasiId OR p.pilihan2 = :formasiId OR p.pilihan3 = :formasiId")
    List<Pilihan> findByFormasiId(@Param("formasiId") Long formasiId);

    @Query("SELECT p.mahasiswa.id FROM Pilihan p WHERE p.id = :pilihanId")
    Long findMahasiswaIdById(@Param("pilihanId") Long pilihanId);
}

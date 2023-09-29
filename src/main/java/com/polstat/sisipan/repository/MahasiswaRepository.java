/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.repository;

/**
 *
 * @author asmuammal
 */
import com.polstat.sisipan.entity.Mahasiswa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {

    @Query("SELECT m FROM Mahasiswa m WHERE m.provinsi.id = :provinsiId")
    List<Mahasiswa> findByProvinsiId(@Param("provinsiId") Long provinsiId);
}

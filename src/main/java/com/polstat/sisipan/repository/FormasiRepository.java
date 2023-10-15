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
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface FormasiRepository extends JpaRepository<Formasi, Long> {

    List<Formasi> findByKuotaStTersediaGreaterThan(int i);

    List<Formasi> findByKuotaKsTersediaGreaterThan(int i);

    List<Formasi> findByKuotaD3TersediaGreaterThan(int i);
}

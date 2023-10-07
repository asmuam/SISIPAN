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
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pilihan", path = "pilihan")
public interface PilihanRepository extends JpaRepository<Pilihan, Long> {

    public List<Pilihan> findByHasil(String pilihan_Sistem);

    public List<Pilihan> findByPilihan3(Formasi pilihan3);

    public List<Pilihan> findByPilihan2(Formasi pilihan2);

    public List<Pilihan> findByPilihan1(Formasi pilihan1);

    public Pilihan findByMahasiswaId(Long mahasiswaId);
}

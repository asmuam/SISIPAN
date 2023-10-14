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
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "formasi", path = "formasi")
//@Api(tags = "Formasi Resource", description = "Operations related to formasi")

public interface FormasiRepository extends JpaRepository<Formasi, Long> {

    public List<Formasi> findByKuotaStTersediaGreaterThan(int i);

    public List<Formasi> findByKuotaKsTersediaGreaterThan(int i);

    public List<Formasi> findByKuotaD3TersediaGreaterThan(int i);
}

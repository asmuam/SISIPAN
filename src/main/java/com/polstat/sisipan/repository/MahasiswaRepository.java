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
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
}

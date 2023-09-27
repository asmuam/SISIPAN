/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author asmuammal
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pilihan")
public class Pilihan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id")
    private Mahasiswa mahasiswa; // Setiap pilihan terkait dengan satu mahasiswa

    @ManyToOne
    @JoinColumn(name = "pilihan_1", referencedColumnName = "id")
    private Formasi pilihan1;

    @ManyToOne
    @JoinColumn(name = "pilihan_2", referencedColumnName = "id")
    private Formasi pilihan2;

    @ManyToOne
    @JoinColumn(name = "pilihan_3", referencedColumnName = "id")
    private Formasi pilihan3;

    @Column(nullable = false)
    private String hasil;
}

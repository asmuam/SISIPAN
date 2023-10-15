/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author asmuammal
 */
@Hidden
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mahasiswa mahasiswa; // Setiap pilihan terkait dengan satu mahasiswa

    @ManyToOne
    @JoinColumn(name = "pilihan_1", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Formasi pilihan1;

    @ManyToOne
    @JoinColumn(name = "pilihan_2", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Formasi pilihan2;

    @ManyToOne
    @JoinColumn(name = "pilihan_3", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Formasi pilihan3;

    @ManyToOne
    @JoinColumn(name = "pilihan_sistem", referencedColumnName = "id",nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Formasi pilihanSistem;

    @Column
    private float indeksPilihan1;
    @Column
    private float indeksPilihan2;
    @Column
    private float indeksPilihan3;
    @Column
    private Float ipk;
    @Column
    private Date waktuMemilih;
    @Column
    private String hasil;
}

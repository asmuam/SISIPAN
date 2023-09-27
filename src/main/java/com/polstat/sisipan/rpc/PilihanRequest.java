/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.rpc;

/**
 *
 * @author asmuammal
 */
public class PilihanRequest {
    private Long idMahasiswa;
    private Long pilihan1;
    private Long pilihan2;
    private Long pilihan3;

    public Long getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(Long idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public Long getPilihan1() {
        return pilihan1;
    }

    public void setPilihan1(Long pilihan1) {
        this.pilihan1 = pilihan1;
    }

    public Long getPilihan2() {
        return pilihan2;
    }

    public void setPilihan2(Long pilihan2) {
        this.pilihan2 = pilihan2;
    }

    public Long getPilihan3() {
        return pilihan3;
    }

    public void setPilihan3(Long pilihan3) {
        this.pilihan3 = pilihan3;
    }
}


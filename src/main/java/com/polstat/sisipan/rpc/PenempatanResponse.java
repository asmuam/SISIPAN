/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.rpc;

import com.polstat.sisipan.entity.Formasi;
import com.polstat.sisipan.entity.Mahasiswa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author asmuammal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenempatanResponse {

    private Mahasiswa mahasiswa;
    private Formasi hasil;
}

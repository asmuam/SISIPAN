/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.repository.ProvinsiRepository;
import com.polstat.sisipan.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController

public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;
    @Autowired
    private ProvinsiRepository provinsiRepository;

}

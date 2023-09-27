/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.service;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.entity.Mahasiswa;
import com.polstat.sisipan.mapper.MahasiswaMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polstat.sisipan.repository.MahasiswaRepository;

/**
 *
 * @author asmuammal
 */

@Service
public class MahasiswaServiceImpl implements MahasiswaService {

    @Autowired
    private MahasiswaRepository memberRepository;

    @Override
    public MahasiswaDto createMahasiswa(MahasiswaDto memberDto) {
        Mahasiswa member = MahasiswaMapper.mapToMahasiswa(memberDto);
        Mahasiswa savedMember = memberRepository.save(member);
        return MahasiswaMapper.mapToMahasiswaDto(savedMember);
    }

    @Override
    public List<MahasiswaDto> getMahasiswa() {
        List<Mahasiswa> members = (List<Mahasiswa>) memberRepository.findAll();
        List<MahasiswaDto> memberDtos = members.stream()
                .map((product) -> (MahasiswaMapper.mapToMahasiswaDto(product)))
                .collect(Collectors.toList());
        return memberDtos;
    }

    @Override
    public MahasiswaDto updateMahasiswa(MahasiswaDto memberDto) {
        Mahasiswa existingMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Mahasiswa updatedMember = memberRepository.save(existingMember);
        return MahasiswaMapper.mapToMahasiswaDto(updatedMember);
    }

    @Override
    public void deleteMahasiswa(MahasiswaDto memberDto) {
        Mahasiswa memberToDelete = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        memberRepository.delete(memberToDelete);
    }

    @Override
    public MahasiswaDto getMahasiswa(Long id) {
        Mahasiswa member = memberRepository.getReferenceById(id);
        return MahasiswaMapper.mapToMahasiswaDto(member);
    }

}

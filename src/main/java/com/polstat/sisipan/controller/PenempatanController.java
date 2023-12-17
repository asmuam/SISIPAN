/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.PenempatanResponse;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.PenempatanService;
import com.polstat.sisipan.service.PilihanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/penempatan")
public class PenempatanController {

    @Autowired
    private PenempatanService penempatanService;
    @Autowired
    private PilihanService pilihanService;

    @Operation(summary = "Do Penempatan")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of pilihan with its result", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping()
    public ResponseEntity<?> getHasilPenempatan(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        try {
            System.out.println("call penempatan");
            penempatanService.penempatan();
            List<PilihanDto> pilihanList = pilihanService.getAllPilihan();
            if (pilihanList.isEmpty()) {
                throw new ResourceNotFoundException("Belum ada mahasiswa yang memilih");
            }
            List<PenempatanResponse> penempatanResponses = penempatanService.getHasilPenempatan();
            if (size > penempatanResponses.size()) {
                size = penempatanResponses.size();
            }
            System.out.println("sukses penempatan");
            SuccessResponse successResponse = new SuccessResponse(penempatanResponses.subList(0, size), "Success", "OK",
                    HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse;
            errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

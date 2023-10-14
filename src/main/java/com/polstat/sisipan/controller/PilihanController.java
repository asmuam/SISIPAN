/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.PilihanRequest;
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
/**
 *
 * @author asmuammal
 */
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pilihan")
public class PilihanController {

    @Autowired
    private PilihanService pilihanService;

    @Autowired
    private PenempatanService penempatanService;

    @Operation(summary = "Get List of pilihan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of pilihan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    @GetMapping()
    public ResponseEntity<?> getPilihan(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        try {
            List<PilihanDto> pilihanList = pilihanService.getAllPilihan();
            if (pilihanList.isEmpty()) {
                throw new ResourceNotFoundException("Belum ada mahasiswa yang memilih");
            }
            SuccessResponse successResponse = new SuccessResponse(pilihanList.subList(0, size), "Success", "OK",
                    HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Create pilihan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data of pilihan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    // Endpoint untuk menambahkan pilihan
    @PostMapping("/{mhs_id}")
    public ResponseEntity<?> tambahPilihan(@PathVariable("mhs_id") Long mahasiswaId,
            @RequestBody PilihanRequest request) {
        try {
            PilihanDto pilihan = pilihanService.createPilihan(mahasiswaId, request.getPilihan1(), request.getPilihan2(),
                    request.getPilihan3());
            penempatanService.penempatan();
            SuccessResponse response = new SuccessResponse();
            response.setData(pilihan);
            response.setMessage("Pilihan berhasil ditambahkan");
            response.setHttpStatus("OK");
            response.setHttpStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Get List pilihan by Formasi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of pilihan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    @GetMapping("/{formasi_id}")
    public ResponseEntity<?> getMahasiswaByFormasi(@PathVariable("formasi_id") Long formasiId) {
        try {
            List<PilihanDto> pilihan = pilihanService.getMahasiswaByFormasi(formasiId);
            SuccessResponse response = new SuccessResponse();
            response.setData(pilihan);
            response.setMessage("Data mahasiswa berdasarkan formasi berhasil diambil");
            response.setHttpStatus("OK");
            response.setHttpStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Replace pilihan with new data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new data of pilihan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    @PutMapping("/{pilihan_id}")
    public ResponseEntity<?> replacePilihan(@PathVariable("pilihan_id") Long pilihanId,
            @RequestBody PilihanDto pilihanDto) {
        try {
            pilihanDto.setId(pilihanId);

            PilihanDto updatedPilihan = pilihanService.updatePilihan(pilihanDto);
            if (updatedPilihan != null) {
                SuccessResponse successResponse = new SuccessResponse(updatedPilihan, "Update Success", "OK",
                        HttpStatus.OK.value());
                return ResponseEntity.ok(successResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Pilihan not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "edit partial data of pilihan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new data of pilihan", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    // Endpoint untuk mengubah pilihan dengan data parsial
    @PatchMapping("/{pilihan_id}")
    public ResponseEntity<?> patchPilihan(@PathVariable("pilihan_id") Long pilihanId,
            @RequestBody PilihanDto pilihanDto) {
        try {
            pilihanDto.setId(pilihanId);
            PilihanDto updatedPilihan = pilihanService.updatePilihan(pilihanDto);
            SuccessResponse response = new SuccessResponse();
            response.setData(updatedPilihan);
            response.setMessage("Pilihan berhasil diperbarui");
            response.setHttpStatus("OK");
            response.setHttpStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Delete pilihan by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "no-content", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    // Endpoint untuk menghapus pilihan
    @DeleteMapping("/{pilihan_id}")
    public ResponseEntity<?> deletePilihan(@PathVariable("pilihan_id") Long pilihanId) {
        try {
            boolean deleted = pilihanService.deletePilihan(pilihanId);
            if (deleted) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setHttpStatus("No Content");
                successResponse.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
                successResponse.setMessage("Mahasiswa deleted successfully");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Mahasiswa not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}

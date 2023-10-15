/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.PilihanDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.PilihanRequest;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.PilihanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    @Operation(summary = "Get List of pilihan.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of pilihan", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "pilihanListExample",
                            value = "{\"data\": [{\"id\": 1, \"mahasiswa\": 1, \"pilihan1\": 101, \"pilihan2\": 102, \"pilihan3\": 103, \"pilihanSistem\": 201, \"indeksPilihan1\": 3.5, \"indeksPilihan2\": 3.6, \"indeksPilihan3\": 3.7, \"ipk\": 3.75, \"waktuMemilih\": \"2023-10-15T12:00:00Z\", \"hasil\": \"Pilihan 1\"}, {\"id\": 2, \"mahasiswa\": 2, \"pilihan1\": 104, \"pilihan2\": 105, \"pilihan3\": 106, \"pilihanSistem\": 202, \"indeksPilihan1\": 3.8, \"indeksPilihan2\": 3.9, \"indeksPilihan3\": 4.0, \"ipk\": 3.95, \"waktuMemilih\": \"2023-10-15T14:00:00Z\", \"hasil\": \"Pilihan 2\"}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
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
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "pilihanSingleExample",
                            value = "{\"data\": [{\"id\": 1, \"mahasiswa\": \"mhs_id\", \"pilihan1\": 101, \"pilihan2\": 102, \"pilihan3\": 103, \"pilihanSistem\": 201, \"indeksPilihan1\": 3.5, \"indeksPilihan2\": 3.6, \"indeksPilihan3\": 3.7, \"ipk\": 3.75, \"waktuMemilih\": \"2023-10-15T12:00:00Z\", \"hasil\": \"Pilihan 1\"}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    // Endpoint untuk menambahkan pilihan
    @PostMapping("/{mhs_id}")
    public ResponseEntity<?> tambahPilihan(@PathVariable("mhs_id") Long mahasiswaId,
            @RequestBody PilihanRequest request) {
        try {
            PilihanDto pilihan = pilihanService.createPilihan(mahasiswaId, request.getPilihan1(), request.getPilihan2(),
                    request.getPilihan3());
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

    @Operation(summary = "Get List pilihan which has desired Formasi.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of pilihan", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "pilihanListExample",
                            value = "{\"data\": [{\"id\": 1, \"mahasiswa\": 1, \"pilihan1\": \"formasi_id\", \"pilihan2\": 102, \"pilihan3\": 103, \"pilihanSistem\": 201, \"indeksPilihan1\": 3.5, \"indeksPilihan2\": 3.6, \"indeksPilihan3\": 3.7, \"ipk\": 3.75, \"waktuMemilih\": \"2023-10-15T12:00:00Z\", \"hasil\": \"Pilihan 2\"}, {\"id\": 2, \"mahasiswa\": 2, \"pilihan1\": 104, \"pilihan2\": \"formasi_id\", \"pilihan3\": 106, \"pilihanSistem\": 202, \"indeksPilihan1\": 3.8, \"indeksPilihan2\": 3.9, \"indeksPilihan3\": 4.0, \"ipk\": 3.95, \"waktuMemilih\": \"2023-10-15T14:00:00Z\", \"hasil\": \"Pilihan 2\"}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

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
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "pilihanSingleExample",
                            value = "{\"data\": [{\"id\": \"pilihan_id\", \"mahasiswa\": 1, \"pilihan1\": 101, \"pilihan2\": 102, \"pilihan3\": 103, \"pilihanSistem\": 201, \"indeksPilihan1\": 3.5, \"indeksPilihan2\": 3.6, \"indeksPilihan3\": 3.7, \"ipk\": 3.75, \"waktuMemilih\": \"2023-10-15T12:00:00Z\", \"hasil\": \"Pilihan 3\"}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @PutMapping("/{pilihan_id}")
    public ResponseEntity<?> replacePilihan(@PathVariable("pilihan_id") Long pilihanId,
            @RequestBody PilihanRequest request) {
        try {
            PilihanDto updatedPilihan = pilihanService.updatePilihan(pilihanId, request);
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
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "pilihanSingleExample",
                            value = "{\"data\": [{\"id\": \"pilihan_id\", \"mahasiswa\": 1, \"pilihan1\": 101, \"pilihan2\": 102, \"pilihan3\": 103, \"pilihanSistem\": 201, \"indeksPilihan1\": 3.5, \"indeksPilihan2\": 3.6, \"indeksPilihan3\": 3.7, \"ipk\": 3.75, \"waktuMemilih\": \"2023-10-15T12:00:00Z\", \"hasil\": \"Pilihan 2\"}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    // Endpoint untuk mengubah pilihan dengan data parsial
    @PatchMapping("/{pilihan_id}")
    public ResponseEntity<?> patchPilihan(@PathVariable("pilihan_id") Long pilihanId,
            @RequestBody PilihanRequest request) {
        try {
            PilihanDto updatedPilihan = pilihanService.updatePilihan(pilihanId, request);
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
            @Content()}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

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

    @Operation(summary = "Delete all pilihan.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping("/delete-all")
    public ResponseEntity<?> deleteAllPilihan() {
        try {
            pilihanService.deleteAllPilihan();
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setHttpStatus("No Content");
            successResponse.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            successResponse.setMessage("All pilihan deleted successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}

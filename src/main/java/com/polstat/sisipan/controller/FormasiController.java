/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.FormasiDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.FormasiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/formasi")
public class FormasiController {

    @Autowired
    private FormasiService formasiService;

    @Operation(summary = "Get All formasi.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Formasi", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class, subTypes = {FormasiDto.class}),
                    examples = @ExampleObject(
                            name = "formasiExample",
                            value = "{\"data\": [{\"id\": 1, \"provinsi\": 1, \"kodeSatker\": \"Kode1\", \"namaSatuanKerja\": \"Satuan Kerja 1\", \"kuotaSt\": 10, \"kuotaKs\": 20, \"kuotaD3\": 30}, {\"id\": 2, \"provinsi\": 2, \"kodeSatker\": \"Kode2\", \"namaSatuanKerja\": \"Satuan Kerja 2\", \"kuotaSt\": 15, \"kuotaKs\": 25, \"kuotaD3\": 35}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    ))
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })

    @GetMapping
    public ResponseEntity<?> getAllFormasi(
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        try {
            List<FormasiDto> formasiList = formasiService.getAllFormasi();
            SuccessResponse successResponse = new SuccessResponse(formasiList.subList(0, size), "Success", "OK",
                    HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Get formasi by ID formasi.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data of Formasi", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "formasiExample",
                            value = "{\"data\": [{\"id\": \"id\", \"provinsi\": 1, \"kodeSatker\": \"Kode1\", \"namaSatuanKerja\": \"Satuan Kerja 1\", \"kuotaSt\": 10, \"kuotaKs\": 20, \"kuotaD3\": 30}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    ))
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormasiById(@PathVariable Long id) {
        try {
            FormasiDto formasi = formasiService.getFormasiById(id);
            if (formasi != null) {
                SuccessResponse successResponse = new SuccessResponse(formasi, "Success", "OK", HttpStatus.OK.value());
                return ResponseEntity.ok(successResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Formasi not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Add formasi.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved Formasi", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "newFormasiExample",
                            value = "{\"data\": [{\"id\": 1, \"provinsi\": 1, \"kodeSatker\": \"Kode1\", \"namaSatuanKerja\": \"Satuan Kerja 1\", \"kuotaSt\": 10, \"kuotaKs\": 20, \"kuotaD3\": 30}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @PostMapping
    public ResponseEntity<?> createFormasi(@RequestBody FormasiDto formasiDto) {
        try {
            FormasiDto createdFormasi = formasiService.createFormasi(formasiDto);
            SuccessResponse successResponse = new SuccessResponse(createdFormasi, "Success", "Created",
                    HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Edit partial Data formasi.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "new Data of Formasi", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "updatedFormasiExample",
                            value = "{\"data\": [{\"id\": \"id\", \"provinsi\": 1, \"kodeSatker\": \"Kode1\", \"namaSatuanKerja\": \"Satuan Kerja 1\", \"kuotaSt\": 10, \"kuotaKs\": 20, \"kuotaD3\": 30}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateFormasi(@PathVariable Long id, @RequestBody FormasiDto formasiDto) {
        try {
            FormasiDto existingFormasi = formasiService.getFormasiById(id);
            if (existingFormasi == null) {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Formasi not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Pemeriksaan dan pembaruan atribut yang ada dalam formasiDto
            if (formasiDto.getKuotaSt() != null) {
                existingFormasi.setKuotaSt(formasiDto.getKuotaSt());
            }
            if (formasiDto.getKuotaKs() != null) {
                existingFormasi.setKuotaKs(formasiDto.getKuotaKs());
            }
            if (formasiDto.getKuotaD3() != null) {
                existingFormasi.setKuotaD3(formasiDto.getKuotaD3());
            }
            // Periksa dan tambahkan atribut lain yang ingin Anda perbarui dalam formasiDto.

            FormasiDto updatedFormasi = formasiService.updateFormasi(existingFormasi);
            SuccessResponse successResponse = new SuccessResponse(updatedFormasi, "Success", "OK",
                    HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Replace with new formasi data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "new data of Formasi", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
                    examples = @ExampleObject(
                            name = "updatedFormasiExample",
                            value = "{\"data\": [{\"id\": \"id\", \"provinsi\": 1, \"kodeSatker\": \"Kode1\", \"namaSatuanKerja\": \"Satuan Kerja 1\", \"kuotaSt\": 10, \"kuotaKs\": 20, \"kuotaD3\": 30}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
                    )
            )}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFormasi(@PathVariable Long id, @RequestBody FormasiDto formasiDto) {
        try {
            formasiDto.setId(id);
            FormasiDto updatedFormasi = formasiService.updateFormasi(formasiDto);
            if (updatedFormasi != null) {
                SuccessResponse successResponse = new SuccessResponse(updatedFormasi, "Success", "OK",
                        HttpStatus.OK.value());
                return ResponseEntity.ok(successResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Formasi not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Delete formasi by ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "No-content", content = {
            @Content()}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFormasi(@PathVariable Long id) {
        try {
            boolean deleted = formasiService.deleteFormasi(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value(),
                        "Formasi not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

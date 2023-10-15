/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.MahasiswaDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.MahasiswaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @Operation(summary = "Get List of Mahasiswa.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Mahasiswa", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
            examples = @ExampleObject(
                name = "mahasiswaListExample",
                value = "{\"data\": [{\"id\": 1, \"nim\": \"12345\", \"name\": \"John Doe\", \"prodi\": \"Teknik Informatika\", \"provinsi\": 1, \"ipk\": 3.75}, {\"id\": 2, \"nim\": \"54321\", \"name\": \"Jane Smith\", \"prodi\": \"Manajemen Bisnis\", \"provinsi\": 2, \"ipk\": 3.95}], \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
            )
            )}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping
    public ResponseEntity<?> getAllMahasiswa() {
        try {
            List<MahasiswaDto> mahasiswaList = mahasiswaService.getMahasiswa();
            SuccessResponse successResponse = new SuccessResponse(mahasiswaList, "Success", "OK",
                    HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Get Data of Mahasiswa based on ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data of Mahasiswa", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
            examples = @ExampleObject(
                name = "mahasiswaExample",
                value = "{\"data\": {\"id\": id, \"nim\": \"12345\", \"name\": \"John Doe\", \"prodi\": \"Teknik Informatika\", \"provinsi\": 1, \"ipk\": 3.75}, \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
            )
            )}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<?> getMahasiswaById(@PathVariable Long id) {
        try {
            MahasiswaDto mahasiswa = mahasiswaService.getMahasiswaById(id);
            if (mahasiswa != null) {
                SuccessResponse successResponse = new SuccessResponse(mahasiswa, "Success", "OK",
                        HttpStatus.OK.value());
                return ResponseEntity.ok(successResponse);
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

    @Operation(summary = "Create Data Mahasiswa.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data of Mahasiswa that has been created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
            examples = @ExampleObject(
                name = "mahasiswaExample",
                value = "{\"data\": {\"id\": 1, \"nim\": \"12345\", \"name\": \"John Doe\", \"prodi\": \"Teknik Informatika\", \"provinsi\": 1, \"ipk\": 3.75}, \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
            ))}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping
    public ResponseEntity<?> createMahasiswa(@RequestBody MahasiswaDto mahasiswaDto) {
        try {
            MahasiswaDto createdMahasiswa = mahasiswaService.createMahasiswa(mahasiswaDto);
            SuccessResponse successResponse = new SuccessResponse(createdMahasiswa, "Success", "Created",
                    HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Update all Data of Mahasiswa.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated Data of Mahasiswa", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
            examples = @ExampleObject(
                name = "mahasiswaExample",
                value = "{\"data\": {\"id\": id, \"nim\": \"12345\", \"name\": \"John Doe\", \"prodi\": \"Teknik Informatika\", \"provinsi\": 1, \"ipk\": 3.75}, \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
            )
            )}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMahasiswa(@PathVariable Long id, @RequestBody MahasiswaDto mahasiswaDto) {
        try {
            mahasiswaDto.setId(id);
            MahasiswaDto updatedMahasiswa = mahasiswaService.updateMahasiswa(mahasiswaDto);
            if (updatedMahasiswa != null) {
                SuccessResponse successResponse = new SuccessResponse(updatedMahasiswa, "Success", "OK",
                        HttpStatus.OK.value());
                return ResponseEntity.ok(successResponse);
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

    @Operation(summary = "Delete Data of Mahasiswa based on ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "", content = {
            @Content()}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = 
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMahasiswa(@PathVariable Long id) {
        try {
            boolean deleted = mahasiswaService.deleteMahasiswa(id);
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

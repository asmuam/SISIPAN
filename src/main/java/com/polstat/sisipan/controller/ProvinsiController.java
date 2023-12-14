/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.FormasiDto;
import com.polstat.sisipan.entity.Provinsi;
import com.polstat.sisipan.repository.ProvinsiRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/provinsi")
public class ProvinsiController {

    @Autowired
    private ProvinsiRepository provinsiRepository;

    @Operation(summary = "Get All provinsi.")
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
    public ResponseEntity<?> getAllProvinsi(
            @RequestParam(name = "size", required = false, defaultValue = "34") int size) {
        try {
            List<Provinsi> provinsiList = provinsiRepository.findAll();
            SuccessResponse successResponse = new SuccessResponse(provinsiList.subList(0, size), "Success", "OK",
                    HttpStatus.OK.value());
            System.out.println("call");
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

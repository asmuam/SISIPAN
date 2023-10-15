/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.controller;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.rpc.ChangePasswordRequest;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Change password of user by ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated Data of user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class),
            examples = @ExampleObject(
                name = "userSingleExample",
                value = "{\"data\": {\"id\": id, \"mahasiswa\": 1, \"email\": \"user@example.com\", \"password\": \"hashedPassword\", \"role\": \"ROLE_USER\", \"authorities\": [{\"authority\": \"ROLE_USER\"}]}, \"message\": \"Success\", \"httpStatus\": \"OK\", \"httpStatusCode\": 200}"
            )
            )}),
        @ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("/{id}/changePassword")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        try {
            UserDto userDto = userService.getUser(id);
            UserDto updatedUser = userService.changePassword(userDto,request);
            if (updatedUser != null) {
                SuccessResponse successResponse = new SuccessResponse(updatedUser, "Success", "OK",
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

    @Operation(summary = "Delete Data of User based on ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "", content = {
            @Content()}),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = 
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.delete(id);
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

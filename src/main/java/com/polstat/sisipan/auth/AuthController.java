/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.auth;

import com.polstat.sisipan.dto.UserDto;
import com.polstat.sisipan.rpc.ErrorResponse;
import com.polstat.sisipan.rpc.SuccessResponse;
import com.polstat.sisipan.service.MahasiswaService;
import com.polstat.sisipan.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asmuammal
 */
@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    MahasiswaService mahasiswaService;

    @Operation(summary = "User login to get access token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email and access token", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            String accessToken = jwtUtil.generateAccessToken(authentication);
            AuthResponse authResponse = new AuthResponse(request.getEmail(), accessToken);
            SuccessResponse response = new SuccessResponse();
            response.setData(authResponse);
            response.setMessage("Login succesfull");
            response.setHttpStatus(HttpStatus.OK.getReasonPhrase());
            response.setHttpStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "User register first before login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "invalid details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            UserDto userDto = UserDto.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .mahasiswa(mahasiswaService.getMahasiswaId(request.getEmail().split("@")[0]))
                    .build();

            UserDto user = userService.createUser(userDto);

            SuccessResponse response = new SuccessResponse();
            response.setData(user);
            response.setMessage("User has been registered successfully.");
            response.setHttpStatus(HttpStatus.OK.getReasonPhrase());
            response.setHttpStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse("Registration Error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}

package com.gudangdamar.main.controller;

import com.gudangdamar.main.dto.LoginRequest;
import com.gudangdamar.main.dto.LoginResponse;
import com.gudangdamar.main.dto.ErrorResponse;  // Import kelas ErrorResponse
import com.gudangdamar.main.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "http://127.0.0.1:5500")  // Menambahkan CORS secara langsung
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Mengotentikasi username dan password
        boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Membuat response dengan token jika autentikasi berhasil
           
            return ResponseEntity.ok(new LoginResponse("Login sudah berhasil"));
        } else {
            // Mengirimkan error dengan kelas ErrorResponse
            return ResponseEntity.status(401).body(new ErrorResponse("Invalid username or password"));
        }
    }
}

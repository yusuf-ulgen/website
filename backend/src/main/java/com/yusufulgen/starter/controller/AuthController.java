package com.yusufulgen.starter.controller;

import com.yusufulgen.starter.dto.request.LoginRequest;
import com.yusufulgen.starter.dto.response.ApiResponse;
import com.yusufulgen.starter.service.AuditLogService;
import com.yusufulgen.starter.service.JwtUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://www.yusufulgen.com", "https://yusufulgen.com"})
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuditLogService auditLogService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            auditLogService.log("LOGIN_SUCCESS", "Kullanıcı başarıyla giriş yaptı: " + loginRequest.getUsername());
            
            return ResponseEntity.ok(ApiResponse.success(token));
        } catch (Exception e) {
            auditLogService.log("LOGIN_FAILED", "Hatalı giriş denemesi: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body(ApiResponse.error(List.of("Hatalı kullanıcı adı veya şifre!")));
        }
    }
}
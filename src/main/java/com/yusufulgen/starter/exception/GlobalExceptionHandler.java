package com.yusufulgen.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.yusufulgen.starter.dto.response.ApiResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Validation Hataları (Bad Request - 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        
        return ResponseEntity.badRequest().body(ApiResponse.error(errors));
    }

    // 2. Genel Hatalar (Internal Server Error - 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralErrors(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(Collections.singletonList("Beklenmedik bir hata oluştu: " + ex.getMessage())));
    }
    
    // 3. Özel bir hata: Kayıt Bulunamadı (Opsiyonel)
    // Buraya EntityNotFoundException gibi özel hataları ekleyebilirsin.
}
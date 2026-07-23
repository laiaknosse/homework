package com.example.mock.controller;

import com.example.mock.dto.LoginRequest;
import com.example.mock.dto.LoginResponse;
import com.example.mock.dto.StatusResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Границы имитируемой задержки ответа, мс
    private static final int MIN_DELAY_MS = 1000;
    private static final int MAX_DELAY_MS = 2000;


    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus() {
        simulateDelay();
        StatusResponse response = new StatusResponse("Login1", "ok");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> postLogin(@Valid @RequestBody LoginRequest request) {
        simulateDelay();
        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        LoginResponse response = new LoginResponse(request.getLogin(), request.getPassword(), currentDate);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        java.util.Map<String, String> errors = new java.util.LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    private void simulateDelay() {
        int delayMs = ThreadLocalRandom.current().nextInt(MIN_DELAY_MS, MAX_DELAY_MS + 1);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

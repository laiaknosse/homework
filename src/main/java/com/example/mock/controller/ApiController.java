package com.example.mock.controller;

import com.example.mock.dto.LoginRequest;
import com.example.mock.dto.LoginResponse;
import com.example.mock.dto.StatusResponse;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final int MIN_DELAY_MS = 1000;
    private static final int MAX_DELAY_MS = 2000;

    @GetMapping("/status")
    public StatusResponse getStatus() {
        delay();
        return new StatusResponse("login", "ok");
    }

    @PostMapping("/login")
    public LoginResponse postLogin(@Nonnull @Valid @RequestBody LoginRequest request) {
        delay();
        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        return new LoginResponse(request.getLogin(), request.getPassword(), currentDate);
    }

    private void delay() {
        int delayMs = ThreadLocalRandom.current().nextInt(MIN_DELAY_MS, MAX_DELAY_MS);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

package com.myapp.controller;

import com.myapp.com.myapp.service.ShortenUrlService;
import com.myapp.dto.ShortenUrlRequestDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class ShortenUrlController {
    private ShortenUrlService shortenUrlService;
    @PostMapping
    public ResponseEntity<?> getShortUrl(ShortenUrlRequestDTO shortenUrlRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(shortenUrlService.getShortenedUrl(shortenUrlRequestDTO));
    }

    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
            response.sendRedirect(shortenUrlService.getOriginalUrl(shortUrl));
    }
}

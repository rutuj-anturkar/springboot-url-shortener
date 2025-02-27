package com.myapp.controller;

import com.myapp.com.myapp.service.ShortenUrlService;
import com.myapp.dto.ShortenUrlRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class ShortenUrlController {
    private ShortenUrlService shortenUrlService;
    @PostMapping
    public ResponseEntity<?> getShortUrl(ShortenUrlRequestDTO shortenUrlRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(shortenUrlService.getShortenedUrl(shortenUrlRequestDTO));
    }
}

package com.myapp.controller;

import com.myapp.com.myapp.service.CacheService;
import com.myapp.com.myapp.service.ShortenUrlService;
import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.dto.ShortenUrlResponseDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class ShortenUrlController {
    private ShortenUrlService shortenUrlService;
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> getShortUrl(ShortenUrlRequestDTO shortenUrlRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(shortenUrlService.getShortenedUrl(shortenUrlRequestDTO), ShortenUrlResponseDTO.class));
    }

    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
            response.sendRedirect(shortenUrlService.getUrlDetails(shortUrl).getOriginalUrl());
    }

    @GetMapping("/urlDetails/{shortUrl}")
    public ResponseEntity<?> getUrlDetails(@PathVariable String shortUrl){
        return ResponseEntity.status((HttpStatus.OK)).body(shortenUrlService.getUrlDetails(shortUrl));
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<?> deleteByShortUrl(@PathVariable String shortUrl){
        shortenUrlService.deleteUrlByShortenedUrl(shortUrl);
        return ResponseEntity.noContent().build();
    }
}

package com.myapp.controller;

import com.myapp.model.ShortenedUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    public DummyController(){
        System.out.print("Inside url-shortner-app-dummyController");
    }

    @GetMapping
    public ResponseEntity<?> getDummyMapping(){
        return ResponseEntity.ok(new ShortenedUrl(null,"shortDummyUrl", "longDummyUrl", LocalDateTime.now()));
    }
}

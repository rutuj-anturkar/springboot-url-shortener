package com.myapp.com.myapp.service;

import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.dto.ShortenUrlResponseDTO;

public interface ShortenUrlService {
    ShortenUrlResponseDTO getShortenedUrl(ShortenUrlRequestDTO shortenUrlRequestDTO);
}

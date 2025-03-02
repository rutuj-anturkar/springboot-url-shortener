package com.myapp.com.myapp.service;

import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.model.ShortenedUrl;

public interface CacheService {
    public ShortenedUrl getShortUrlByOriginalUrl(String originalUrl);
    public void deleteByShortAndLongUrl(String shortUrl, String longUrl);
}

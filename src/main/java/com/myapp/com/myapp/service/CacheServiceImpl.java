package com.myapp.com.myapp.service;

import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.model.ShortenedUrl;
import com.myapp.repository.ShortenedUrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CacheServiceImpl implements CacheService{
    private ShortenedUrlRepository shortenedUrlRepository;

    @Override
    @Cacheable(value = "original_to-short", key ="#originalUrl", unless = "#result == null")
    public ShortenedUrl getShortUrlByOriginalUrl(String originalUrl) {
        ShortenedUrl shortenedUrl = shortenedUrlRepository.findByOriginalUrl(originalUrl).orElse(null);
        return  shortenedUrl;
    }

    @Caching(evict = {
            @CacheEvict(value = "urlDetails", key = "#shortUrl"),
            @CacheEvict(value = "original_to-short", key = "#longUrl")
    }
    )
    public void deleteByShortAndLongUrl(String shortUrl, String longUrl) {
        shortenedUrlRepository.deleteByShortenedUrl(shortUrl);
    }
}

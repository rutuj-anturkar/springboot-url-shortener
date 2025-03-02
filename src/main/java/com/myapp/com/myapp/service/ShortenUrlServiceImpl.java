package com.myapp.com.myapp.service;

import com.myapp.custom_exceptions.ResourceNotFoundException;
import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.dto.ShortenUrlResponseDTO;
import com.myapp.dto.UrlDetailsDTO;
import com.myapp.model.ShortenedUrl;
import com.myapp.repository.ShortenedUrlRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ShortenUrlServiceImpl implements ShortenUrlService {
    private ShortenedUrlRepository shortenedUrlRepository;
    private ModelMapper modelMapper;
    private CacheService cacheService;

    @CachePut(value = "urlDetails", key = "#result.shortenedUrl")
    @Override
    public UrlDetailsDTO getShortenedUrl(ShortenUrlRequestDTO shortenUrlRequestDTO) {
        ShortenedUrl result = cacheService.getShortUrlByOriginalUrl(shortenUrlRequestDTO.getOriginalUrl());

        if (result != null) {
            return modelMapper.map(result, UrlDetailsDTO.class);
        }

        String shortenedUrl = generateShortenedUrl(shortenUrlRequestDTO.getOriginalUrl());

        while (!shortenedUrlRepository.findByShortenedUrl(shortenedUrl).isEmpty()) {
            shortenedUrl = generateShortenedUrl(shortenUrlRequestDTO.getOriginalUrl());
        }

        ShortenedUrl shortenedUrlInstance = new ShortenedUrl();
        shortenedUrlInstance.setShortenedUrl(shortenedUrl);
        shortenedUrlInstance.setOriginalUrl(shortenUrlRequestDTO.getOriginalUrl());
        shortenedUrlRepository.save(shortenedUrlInstance);
        System.out.println("In put: " + modelMapper.map(shortenedUrlInstance, UrlDetailsDTO.class).getShortenedUrl());
        return modelMapper.map(shortenedUrlInstance, UrlDetailsDTO.class);
    }

    @Override
    @Cacheable(value = "urlDetails", key = "#shortUrl")
    public UrlDetailsDTO getUrlDetails(String shortUrl) {
        ShortenedUrl shortenedUrlResult = shortenedUrlRepository.findByShortenedUrl(shortUrl).orElseThrow(() -> new ResourceNotFoundException("Redirect Scenario: Short url is invalid"));

        return modelMapper.map(shortenedUrlResult, UrlDetailsDTO.class);
    }

    @Override
    public void deleteUrlByShortenedUrl(String shortUrl) {
        String originalUrl = this.getUrlDetails(shortUrl).getOriginalUrl();
        cacheService.deleteByShortAndLongUrl(shortUrl, originalUrl);
    }

    private String generateShortenedUrl(String longUrl) {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}

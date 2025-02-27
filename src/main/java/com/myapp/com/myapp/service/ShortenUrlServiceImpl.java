package com.myapp.com.myapp.service;

import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.dto.ShortenUrlResponseDTO;
import com.myapp.model.ShortenedUrl;
import com.myapp.repository.ShortenedUrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ShortenUrlServiceImpl implements ShortenUrlService {
    private ShortenedUrlRepository shortenedUrlRepository;

    @Override
    public ShortenUrlResponseDTO getShortenedUrl(ShortenUrlRequestDTO shortenUrlRequestDTO) {
        List<ShortenedUrl> queryResults = shortenedUrlRepository.findByOriginalUrl(shortenUrlRequestDTO.getOriginalUrl());
        if (queryResults.size()>0) {
            return new ShortenUrlResponseDTO(queryResults.get(0).getShortenedUrl());
        }

        String shortenedUrl = getShortenedUrl(shortenUrlRequestDTO.getOriginalUrl());

        while (!shortenedUrlRepository.findByShortenedUrl(shortenedUrl).isEmpty()) {
            shortenedUrl = getShortenedUrl(shortenUrlRequestDTO.getOriginalUrl());
        }

        ShortenedUrl shortenedUrlInstance = new ShortenedUrl();
        shortenedUrlInstance.setShortenedUrl(shortenedUrl);
        shortenedUrlInstance.setOriginalUrl(shortenUrlRequestDTO.getOriginalUrl());
        shortenedUrlRepository.save(shortenedUrlInstance);

        return new ShortenUrlResponseDTO(shortenedUrl);
    }

    private String getShortenedUrl(String longUrl) {
        return UUID.randomUUID().toString().substring(0, 6);

    }
}
